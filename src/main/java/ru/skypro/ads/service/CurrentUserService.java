package ru.skypro.ads.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.NewPasswordDto;
import ru.skypro.ads.dto.UserDto;


public interface CurrentUserService {

    /**
     * Изменение пароля зарегистрированного пользователя
     *
     * @param newPasswordDto {@link NewPasswordDto} объект, содержащий текущий и новый пароли
     * @param authentication {@link Authentication}
     * @return <code>true</code> если пароль изменен, <code>false</code> в случае неудачи
     */
    boolean setPassword(NewPasswordDto newPasswordDto, Authentication authentication);

    /**
     * Получение информации об зарегистрированном пользователе
     *
     * @param authentication {@link Authentication}
     * @return Объект {@link UserDto}
     */
    UserDto getUser(Authentication authentication);

    /**
     * Изменение информации об зарегистрированном пользователе
     *
     * @param userDto        новая информация об пользователе
     * @param authentication {@link Authentication}
     * @return {@link UserDto} обновленные данные, в случае успешного изменения
     */
    UserDto updateUser(UserDto userDto, Authentication authentication);

    /**
     * Импортирует изображение для аватарки зарегистрированном пользователя
     *
     * @param image          объект {@link MultipartFile}
     * @param authentication {@link Authentication}
     * @return <code>true</code> если изображение загружено, <code>false</code> в случае неудачи
     */
    boolean updateUserImage(MultipartFile image, Authentication authentication);

}
