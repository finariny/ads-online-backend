package ru.skypro.ads.dto;

import lombok.Data;

@Data
public abstract class UserDto {
    protected Integer id;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String image;
}
