package ru.skypro.ads.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;

@Data
public class UserDto {
    @Positive
    protected Integer id;
    @Email
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String image;
}
