package ru.skypro.ads.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.Ads;
import ru.skypro.ads.dto.CreateAds;
import ru.skypro.ads.dto.ResponseWrapperAds;
import ru.skypro.ads.service.AdsService;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Collection;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:8080")
public class AdsController {

    private final AdsService adsService;

    @Operation(summary = "Получить все объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(
                                            implementation = ResponseWrapperAds.class)))),
            })
    @GetMapping()
    public ResponseEntity<Collection<Ads>> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @Operation(
            summary = "Добавить объявление",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(
                                            implementation = ResponseWrapperAds.class)))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping()
    public ResponseEntity<Ads> addAd(
                                     @RequestPart("properties") @Valid @NotNull @NotBlank CreateAds properties,
                                     @RequestPart("image") @Valid @NotNull @NotBlank MultipartFile image
    ) {
        if (adsService.save(properties, image)==null){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(adsService.save(properties, image));
    }

    @Operation(
            summary = "Получить информацию об объявлении",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(
                                            implementation = ResponseWrapperAds.class)))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Ads> getAd(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.getAd(id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить объявление")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Void> deleteAd(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (adsService.deleteAd(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("{id}")
    @Operation(summary = "Обновить информацию об объявлении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(
                                    implementation = Ads.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Ads> updateAd(@PathVariable int id, @RequestBody CreateAds createAds) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (adsService.updateAd(id, createAds)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @Operation(summary = "Получить объявления авторизованного пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(
                                            implementation = ResponseWrapperAds.class)))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            })
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(@NotNull Authentication authentication) {
        return ResponseEntity.ok(adsService.getAdsMe(authentication));
    }

    @Operation(summary = "Обновить картинку объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                                    array = @ArraySchema(schema = @Schema(
                                            implementation = String.class)))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            })

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(
            @PathVariable Integer id,
            @RequestPart(value = "image") MultipartFile file
    ) {
        return ResponseEntity.ok(adsService.updateImage(id,file));
    }



}
