package ru.skypro.ads.dto;

import lombok.Data;

@Data
public class NewPassword {
    private String currentPassword;
    private String newPassword;
}
