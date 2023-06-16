package ru.skypro.ads.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.entity.Ads;
import ru.skypro.ads.entity.ImageInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;

public interface ImageService {
    void getImageFromDisk(HttpServletResponse response, Path filePath, ImageInterface imageDetails) throws IOException;

    void updateAdsImageDetails(Ads ads, MultipartFile image, Path filePath);

    String getExtension(String fileName);

    void saveFileOnDisk(MultipartFile image, Path filePath) throws IOException;
}
