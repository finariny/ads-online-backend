package ru.skypro.ads.dto;

import lombok.Data;

import java.util.List;

@Data
public class Comment {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Integer createdAt;
    private Integer pk;
    private String text;
}
