package ru.skypro.ads.service;

import ru.skypro.ads.entity.User;

public interface PermissionService {

    /**
     * Проверяет, является ли пользователь владельцем объявления / комментария
     *
     * @param email e-mail пользователя
     * @param owner владелец объявления / комментария
     * @return <code>true</code> если пользователь является владельцем, <code>false</code> в случае неудачи
     */
    boolean isThisUser(String email, User owner);

    /**
     * Проверяет, является ли пользователь администратором
     *
     * @param user пользователь
     * @return <code>true</code> если пользователь является администратором, <code>false</code> в случае неудачи
     */
    boolean isAdmin(User user);

    /**
     * Проверяет, является ли пользователь владельцем объявления / комментария или администратором
     *
     * @param email e-mail пользователя / администратора
     * @param owner владелец объявления / комментария
     * @return <code>true</code> если пользователь является владельцем или администратором, <code>false</code> в случае неудачи
     */
    boolean isThisUserOrAdmin(String email, User owner);
}
