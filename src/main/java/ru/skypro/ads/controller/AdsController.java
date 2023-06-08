package ru.skypro.ads.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.AdsDto;
import ru.skypro.ads.dto.CreateAdsDto;
import ru.skypro.ads.dto.ResponseWrapperAdsDto;
import ru.skypro.ads.service.AdsService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Объявления")
public class AdsController {

    @Autowired
    private AdsService adsService;

    @Operation(summary = "Получить все объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(
                                    implementation = ResponseWrapperAdsDto.class)))),
    })
    @GetMapping()
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @Operation(summary = "Добавить объявление")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(
                                    implementation = ResponseWrapperAdsDto.class)))),
            @ApiResponse(responseCode = "401")
    })
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAd(@NotNull Authentication authentication,
            @RequestPart("properties") @Valid @NotNull @NotBlank CreateAdsDto properties,
            @RequestPart("image") @Valid @NotNull @NotBlank MultipartFile image
    ) {
        return ResponseEntity.ok(adsService.saveAd(properties,authentication.getName(), image));
    }

    @Operation(summary = "Получить информацию об объявлении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(
                                    implementation = ResponseWrapperAdsDto.class)))),
            @ApiResponse(responseCode = "401")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdsDto> getAds(@PathVariable int id) {
        return ResponseEntity.ok(adsService.getAd(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить объявление")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")
    })
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> removeAd(@NotNull Authentication authentication, @PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (adsService.removeAd(authentication.getName(), id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновить информацию об объявлении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(
                                    implementation = AdsDto.class)))),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")
    })
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<AdsDto> updateAds(@NotNull Authentication authentication, @PathVariable int id, @RequestBody CreateAdsDto createAdsDto) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (adsService.updateAds(id, createAdsDto) != null) {
            return ResponseEntity.ok(adsService.updateAds(id, createAdsDto));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @Operation(summary = "Получить объявления авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(
                                    implementation = ResponseWrapperAdsDto.class)))),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized")
    })
    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe(@NotNull Authentication authentication) {
        ResponseWrapperAdsDto ads = adsService.getAdsMe(authentication);
        if (ads != null) {
            return ResponseEntity.ok(ads);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Обновить картинку объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                            array = @ArraySchema(schema = @Schema(
                                    implementation = String.class)))),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized"),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden")
    })
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateImage(@NotNull Authentication authentication, @PathVariable("id") int id,
                                         @RequestPart(value = "image") @Valid MultipartFile image
    ) {
        if (adsService.updateImage(id, image)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
