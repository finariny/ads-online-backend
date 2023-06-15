package ru.skypro.ads.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum содержащий роль(автор объявления или администратор), который позволит разделить
 * уровень возможностей и доступа к функциям для пользователей.
 */
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
