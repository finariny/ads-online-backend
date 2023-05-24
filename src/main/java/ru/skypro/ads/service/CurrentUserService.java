package ru.skypro.ads.service;

import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.UserReq;
import ru.skypro.ads.dto.UserResp;

public interface CurrentUserService {

    /**
     * Изменение пароля зарегистрированного пользователя
     *
     * @param currentPassword текущий пароль
     * @param newPassword     новый пароль
     * @return <code>true</code> если пароль изменен, <code>false</code> в случае неудачи
     */
    boolean setPassword(@NotNull String currentPassword, @NotNull String newPassword);

    /**
     * Получение информации об зарегистрированном пользователе
     *
     * @return {@link UserResp}
     */
    UserResp getInfo();

    /**
     * Изменение информации об зарегистрированном пользователе
     *
     * @param userRequest новая информация об пользователе
     * @return {@link UserResp} обновленные данные, в случае успешного изменения
     */
    UserResp updateInfo(@NotNull UserReq userRequest);

    /**
     * Импортирует изображение для аватарки зарегистрированном пользователя
     *
     * @param image объект {@link MultipartFile}
     * @return <code>true</code> если изображение загружено, <code>false</code> в случае неудачи
     */
    boolean importImage(@NotNull MultipartFile image);

}
