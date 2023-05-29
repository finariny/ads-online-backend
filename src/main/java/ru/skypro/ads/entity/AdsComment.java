package ru.skypro.ads.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * Сущность для комментариев к объявлениям
 *  связанные таблицы User (много комментов к одному User)
 *  и лист с комментариями (много комментов у одного Ads)
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdsComment {

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