package ru.skypro.ads.service;

import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.User;

import java.util.Optional;

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
     * @return Объект-контейнер сущности {@link User}
     */
    Optional<User> getUser();

    /**
     * Изменение информации об зарегистрированном пользователе
     *
     * @param user новая информация об пользователе
     * @return {@link User} с обновленными данными, в случае успешного изменения
     */
    Optional<User> updateUser(@NotNull User user);

    /**
     * Импортирует изображение для аватарки зарегистрированном пользователя
     *
     * @param image объект {@link MultipartFile}
     * @return <code>true</code> если изображение загружено, <code>false</code> в случае неудачи
     */
    boolean updateUserImage(@NotNull MultipartFile image);

}
