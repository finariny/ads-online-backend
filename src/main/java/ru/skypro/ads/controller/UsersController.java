package ru.skypro.ads.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.NewPassword;
import ru.skypro.ads.dto.User;
import ru.skypro.ads.service.CurrentUserService;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:8080")
@Tag(name = "Пользователи")
public class UsersController {

    private final CurrentUserService currentUserService;

    public UsersController(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "401"),
                    @ApiResponse(responseCode = "403")})
    public ResponseEntity<?> setPassword(@RequestBody @Valid NewPassword newPassword) {
        if (currentUserService.setPassword(newPassword.getCurrentPassword(), newPassword.getNewPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/me")
    @Operation(summary = "Получить информацию об авторизованном пользователе")
    @ApiResponses(value = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "401")})
    public ResponseEntity<User> getUser() {
        return ResponseEntity.of(currentUserService.getUser());
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновить информацию об авторизованном пользователе")
    @ApiResponses(value = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "401")})
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user) {
        return ResponseEntity.of(currentUserService.updateUser(user));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновить аватар авторизованного пользователя")
    @ApiResponses(value = {@ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")})
    public ResponseEntity<?> updateUserImage(@RequestBody @Valid MultipartFile image) {
        if (currentUserService.updateUserImage(image)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
