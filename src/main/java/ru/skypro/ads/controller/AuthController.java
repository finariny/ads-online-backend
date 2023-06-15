package ru.skypro.ads.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.ads.dto.LoginReqDto;
import ru.skypro.ads.dto.RegisterReqDto;
import ru.skypro.ads.dto.RoleDto;
import ru.skypro.ads.service.AuthService;

import static ru.skypro.ads.dto.RoleDto.USER;

@Log4j2
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Tag(name = "Регистрация")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerReqDto) {
        log.info("Нажат контроллер /register");
//        RoleDto roleDto = registerReqDto.getRoleDto() == null ? USER : registerReqDto.getRoleDto();
        log.info("RoleDTO = " + registerReqDto.getUsername());
        if (authService.register(registerReqDto)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Tag(name = "Авторизация")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto req) {
        log.info("Нажали контроллер Авторизация!");
        if (authService.login(req.getUsername(), req.getPassword())) {
            log.info("ввели логин: " + req.getUsername());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
