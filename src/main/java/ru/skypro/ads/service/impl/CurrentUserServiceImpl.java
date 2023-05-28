package ru.skypro.ads.service.impl;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.User;
import ru.skypro.ads.service.CurrentUserService;

import java.util.Optional;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private User user;
    private String currentPassword;

    @Value("${app.min.passwd.length}")
    private int minPasswdLength;

    {
        user = new User();
        user.setId(10001);
        user.setEmail("user@email.local");
        user.setPhone("+79123456780");
        user.setImage("image_d5fv6d9fvdfv");
        user.setLastName("Толстой");
        user.setFirstName("Лев");

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
     * @return {@link User}
     */
    @Override
    public Optional<User> getUser() {
        // TODO: 24.05.2023
        return Optional.of(this.user);
    }

    /**
     * Изменение информации об зарегистрированном пользователе
     *
     * @param user новая информация об пользователе
     * @return {@link User} обновленные данные, в случае успешного изменения
     */
    @Override
    public Optional<User> updateUser(User user) {
        // TODO: 24.05.2023
        this.user = user;
        return Optional.of(this.user);
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
