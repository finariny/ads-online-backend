package ru.skypro.ads.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.NewPasswordDto;
import ru.skypro.ads.dto.UserDto;
import ru.skypro.ads.entity.User;
import ru.skypro.ads.exception.UserNotFoundException;
import ru.skypro.ads.mapper.UserMapper;
import ru.skypro.ads.repository.UserRepository;
import ru.skypro.ads.service.CurrentUserService;

@Log4j2
@Service
@RequiredArgsConstructor
public class CurrentUserServiceImpl implements CurrentUserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    /**
     * Изменение пароля зарегистрированного пользователя
     *
     * @param newPasswordDto {@link NewPasswordDto} объект, содержащий текущий и новый пароли
     * @param authentication {@link Authentication}
     * @return <code>true</code> если пароль изменен, <code>false</code> в случае неудачи
     */
    @Override
    public boolean setPassword(NewPasswordDto newPasswordDto, Authentication authentication) {
        System.out.println("Внутри метода SetPassword");
        try {
            User user = userRepository
                    .findUserByEmail(authentication.getName())
                    .orElseThrow(UserNotFoundException::new);
            if (!newPasswordDto.getCurrentPassword().equals(user.getPassword())) {
                throw new RuntimeException("Не совпадают пароли");
            }
            user.setPassword(newPasswordDto.getNewPassword());
            System.out.println("newPasswordDto.getNewPassword()");
            userRepository.save(user);
        } catch (Exception e) {
            log.warn("Не удалось изменить пароль: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Получение информации об зарегистрированном пользователе
     *
     * @param authentication {@link Authentication}
     * @return Объект {@link UserDto}
     */
    @Override
    public UserDto getUser(Authentication authentication) {
        System.out.println("печатаю это до взаимодействия с БД");
        User user = userRepository
                .findUserByEmail(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        System.out.println("Запрошенная информация: "+ userMapper.userToUserDto(user));
        return userMapper.userToUserDto(user);
    }

    /**
     * Изменение информации об зарегистрированном пользователе
     *
     * @param userDto        новая информация об пользователе
     * @param authentication {@link Authentication}
     * @return {@link UserDto} обновленные данные, в случае успешного изменения
     */
    @Override
    public UserDto updateUser(UserDto userDto, Authentication authentication) {
        User authenticatedUser = userRepository
                .findUserByEmail(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        if(userDto.getFirstName()!=null&&!userDto.getFirstName().isBlank()){
            authenticatedUser.setFirstName(userDto.getFirstName());
        }
        if(userDto.getLastName()!=null&&!userDto.getLastName().isBlank()){
            authenticatedUser.setLastName(userDto.getLastName());
        }
        if(userDto.getPhone()!=null&&!userDto.getPhone().isBlank()){
            authenticatedUser.setPhone(userDto.getPhone());
        }
        System.out.println();
        System.out.println(authenticatedUser);
        return userMapper.userToUserDto(userRepository.save(authenticatedUser));

    }

    /**
     * Импортирует изображение для аватарки зарегистрированном пользователя
     *
     * @param image          объект {@link MultipartFile}
     * @param authentication
     * @return <code>true</code> если изображение загружено, <code>false</code> в случае неудачи
     */
    @Override
    public boolean updateUserImage(MultipartFile image, Authentication authentication) {
        return false;
    }
}
