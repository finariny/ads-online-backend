package ru.skypro.ads.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.UserReq;
import ru.skypro.ads.dto.UserResp;
import ru.skypro.ads.service.CurrentUserService;

public class CurrentUserServiceImpl implements CurrentUserService {

    /**
     * Изменение пароля зарегистрированного пользователя
     *
     * @param currentPassword текущий пароль
     * @param newPassword     новый пароль
     * @return <code>true</code> если пароль изменен, <code>false</code> в случае неудачи
     */
    @Override
    public boolean setPassword(String currentPassword, String newPassword) {
        // TODO: 24.05.2023  
        return false;
    }

    /**
     * Получение информации об зарегистрированном пользователе
     *
     * @return {@link UserResp}
     */
    @Override
    public UserResp getInfo() {
        // TODO: 24.05.2023  
        return null;
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
        return null;
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
