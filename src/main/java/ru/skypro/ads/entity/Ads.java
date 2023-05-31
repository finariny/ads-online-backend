package ru.skypro.ads.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность Объявления
 * связанные таблицы UserDto (много AdsDto к одному UserDto)
 * и лист с комментариями (у одного AdsDto много комментов)
 */
@Entity
@Table(name = "ads")
@Data
@NoArgsConstructor(force = true)
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer price;
    private String title;
    private String image;
    private String description;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> adsCommentList;
}
