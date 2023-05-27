package ru.skypro.ads.service.impl;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.UserReq;
import ru.skypro.ads.dto.UserResp;
import ru.skypro.ads.service.CurrentUserService;

import java.util.Optional;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private UserResp userResp;
    private String currentPassword;

    @Value("${app.min.passwd.length}")
    private int minPasswdLength;

    {
        userResp = new UserResp();
        userResp.setId(10001);
        userResp.setEmail("user@email.local");
        userResp.setPhone("+79123456780");
        userResp.setImage("image_d5fv6d9fvdfv");
        userResp.setLastName("Толстой");
        userResp.setFirstName("Лев");

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
     * @return {@link UserResp}
     */
    @Override
    public Optional<UserResp> getUser() {
        // TODO: 24.05.2023
        return Optional.of(this.userResp);
    }

    /**
     * Изменение информации об зарегистрированном пользователе
     *
     * @param userRequest новая информация об пользователе
     * @return {@link UserResp} обновленные данные, в случае успешного изменения
     */
    @Override
    public UserResp updateInfo(UserReq userRequest) {
        // TODO: 24.05.2023
//        this.userResp;
        return this.userResp;
    }

    /**
     * Импортирует изображение для аватарки зарегистрированном пользователя
     *
     * @param image объект {@link MultipartFile}
     * @return <code>true</code> если изображение загружено, <code>false</code> в случае неудачи
     */
    @Override
    public boolean importImage(MultipartFile image) {
        // TODO: 24.05.2023
        return false;
    }
}
