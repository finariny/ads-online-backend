package ru.skypro.ads.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.AdsDto;
import ru.skypro.ads.dto.ResponseWrapperAdsDto;
import ru.skypro.ads.service.AdsService;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.crypto.OctetStreamData;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    // TODO: 23.05.2023 see openapi.yaml

    private final AdsService adsService;

    @Operation(summary = "Получить все объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(
                                            implementation = ResponseWrapperAdsDto.class)))),
            })
    @GetMapping()
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        ResponseWrapperAdsDto ads = new ResponseWrapperAdsDto(adsService.getAllAds());
        return ResponseEntity.ok(ads);
    }
    @Operation(
            summary = "Добавить объявление",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(
                                    implementation = ResponseWrapperAdsDto.class)))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping()
    public ResponseEntity<AdsDto> addAds(@NotNull Authentication authentication,
                                         @RequestPart("properties") @Valid @javax.validation.constraints.NotNull @NotBlank Object properties,
                                         @RequestPart("image") @Valid @javax.validation.constraints.NotNull @NotBlank MultipartFile image
    ) {
        return ResponseEntity.ok(adsService.save(properties, authentication.getName(), image));
    }

    @Operation(
            summary = "Получить информацию об объявлении",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(
                                            implementation = ResponseWrapperAdsDto.class)))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<AdsDto> addAds( @PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.getAds(id));
    }



}
