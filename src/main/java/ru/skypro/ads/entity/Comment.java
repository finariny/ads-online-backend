package ru.skypro.ads.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность для комментариев к объявлениям
 * связанные таблицы UserDto (много комментов к одному UserDto)
 * и лист с комментариями (много комментов у одного AdsDto)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime createdAt;
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Ads ads;
}
