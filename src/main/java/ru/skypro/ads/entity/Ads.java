package ru.skypro.ads.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Objects;

/**
 * Сущность Объявления
 * связанные таблицы UserDto (много AdsDto к одному UserDto)
 * и лист с комментариями (у одного AdsDto много комментов)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@Table(name = "ADS", indexes = {
        @Index(name = "IDX_ADS_USER", columnList = "USER_ID")
})
public class Ads {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @PositiveOrZero
    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "ads")
    private List<Comment> adsCommentList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ads)) return false;
        Ads ads = (Ads) o;
        return Objects.equals(getId(), ads.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
