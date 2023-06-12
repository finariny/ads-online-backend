package ru.skypro.ads.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.ads.dto.CustomUserDetails;
import ru.skypro.ads.dto.RegisterReqDto;
import ru.skypro.ads.entity.Role;
import ru.skypro.ads.entity.User;
import ru.skypro.ads.exception.IncorrectUsernameException;
import ru.skypro.ads.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.getUserByEmailIgnoreCase(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void createUser(RegisterReqDto registerReq) {
        if (userRepository.findUserByEmail(registerReq.getUsername()).isPresent()) {
            throw new IncorrectUsernameException();
        }
        User user = new User();
        user.setEmail(registerReq.getUsername());
        user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        user.setRole(Role.USER);
        user.setFirstName(registerReq.getFirstName());
        user.setLastName(registerReq.getLastName());
        user.setPhone(registerReq.getPhone());
        System.out.println("Метод: createUser. Перед сохранением в БД");
        System.out.println(user);
        userRepository.save(user);
    }

}
