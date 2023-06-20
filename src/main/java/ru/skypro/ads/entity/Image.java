package ru.skypro.ads.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.nio.file.Path;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "IMAGE")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "FILE_PATH", nullable = false)
    private String filePath;

    @Column(name = "FILE_EXTENSION", nullable = false)
    private String fileExtension;

    @Column(name = "FILE_SIZE", nullable = false)
    private long fileSize;

    @Column(name = "MEDIA_TYPE", nullable = false)
    private String mediaType;

    @OneToOne(mappedBy = "image")
    private Ads ads;

    public Path getPath() {
        return Path.of(this.filePath);
    }

    public String getUrl() {
        return String.format("/%s/%d", getPath().getParent(), getId());
    }

    public String getMediaType() {
        return mediaType;
    }

    public long getFileSize() {
        return fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image = (Image) o;
        return getId() == image.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return getFilePath();
    }
}
