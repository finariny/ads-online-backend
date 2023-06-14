package ru.skypro.ads.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.ads.dto.RegisterReqDto;
import ru.skypro.ads.service.AuthService;

@Log4j2
@Service
public class AuthServiceImpl implements AuthService {

    private final CustomUserDetailsService manager;

    private final PasswordEncoder encoder;

    public AuthServiceImpl(CustomUserDetailsService manager, PasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterReqDto registerReq) {
        manager.createUser(registerReq);
        log.info("Зарегистрирован новый пользователь: " + registerReq.getUsername());
        return true;
    }
}
