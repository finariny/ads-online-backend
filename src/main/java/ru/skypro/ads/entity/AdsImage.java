package ru.skypro.ads.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ads_image")
public class AdsImage implements ImageInterface {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
//    private int id;
    private String id;
    @Column(name = "file_path", nullable = false)
    private String filePath;
    @Column(name = "file_extension", nullable = false)
    private String fileExtension;
    @Column(name = "file_size", nullable = false)
    private long fileSize;
    @Column(name = "media_type", nullable = false)
    private String mediaType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ads_id", foreignKey = @ForeignKey(name = "fk_ads_image_ads"))
    private Ads ads;

    @Override
    public String getMediaType() {
        return mediaType;
    }

    @Override
    public long getFileSize() {
        return fileSize;
    }


}
