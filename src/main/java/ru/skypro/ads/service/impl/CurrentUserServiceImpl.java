package ru.skypro.ads.service.impl;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.UserDto;
import ru.skypro.ads.service.CurrentUserService;

import java.util.Optional;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private UserDto userDto;
    private String currentPassword;

    @Value("${app.min.passwd.length}")
    private int minPasswdLength;

    {
        userDto = new UserDto();
        userDto.setId(10001);
        userDto.setEmail("userDto@email.local");
        userDto.setPhone("+79123456780");
        userDto.setImage("image_d5fv6d9fvdfv");
        userDto.setLastName("Толстой");
        userDto.setFirstName("Лев");

        currentPassword = "password";
    }

    /**
     * Изменение пароля зарегистрированного пользователя
     *
     * @param currentPassword текущий пароль
     * @param newPassword     новый пароль
     * @return <code>true</code> если пароль изменен, <code>false</code> в случае неудачи
     */
    @Override
    public boolean setPassword(@NotNull String currentPassword, @NotNull String newPassword) {
        // TODO: 24.05.2023

        if (this.currentPassword.equals(currentPassword) && newPassword.length() > minPasswdLength) {
            this.currentPassword = newPassword;
            return true;
        }
        return false;
    }

    /**
     * Получение информации об зарегистрированном пользователе
     *
     * @return {@link UserDto}
     */
    @Override
    public Optional<UserDto> getUser() {
        // TODO: 24.05.2023
        return Optional.of(this.userDto);
    }

    /**
     * Изменение информации об зарегистрированном пользователе
     *
     * @param userDto новая информация об пользователе
     * @return {@link UserDto} обновленные данные, в случае успешного изменения
     */
    @Override
    public Optional<UserDto> updateUser(UserDto userDto) {
        // TODO: 24.05.2023
        this.userDto = userDto;
        return Optional.of(this.userDto);
    }

    /**
     * Импортирует изображение для аватарки зарегистрированном пользователя
     *
     * @param image объект {@link MultipartFile}
     * @return <code>true</code> если изображение загружено, <code>false</code> в случае неудачи
     */
    @Override
    public boolean updateUserImage(MultipartFile image) {
        // TODO: 24.05.2023
        return false;
    }
}
