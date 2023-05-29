package ru.skypro.ads.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.awt.*;
import java.util.List;
/**
 * Сущность Объявления
 * связанные таблицы User (много Ads к одному User)
 *  и лист с комментариями (у одного Ads много комментов)
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
    private List<AdsComment> adsCommentList;

}