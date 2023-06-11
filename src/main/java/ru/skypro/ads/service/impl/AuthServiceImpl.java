package ru.skypro.ads.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.ads.dto.RegisterReqDto;
import ru.skypro.ads.exception.IncorrectArgumentRegException;
import ru.skypro.ads.service.AuthService;

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
    if(registerReq.getUsername() == null || registerReq.getUsername().isBlank()
            || registerReq.getFirstName() == null || registerReq.getFirstName().isBlank()
            || registerReq.getLastName() == null || registerReq.getLastName().isBlank()
            || registerReq.getPhone() == null || registerReq.getPhone().isBlank()
            || registerReq.getPassword() == null || registerReq.getPassword().isBlank()) throw new IncorrectArgumentRegException();

    System.out.println("Зарегистрирован новый пользователь: "+ registerReq.getUsername());
    manager.createUser(registerReq);
    return true;
  }
}
