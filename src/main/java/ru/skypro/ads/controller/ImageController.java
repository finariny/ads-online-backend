package ru.skypro.ads.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.ads.entity.Ads;
import ru.skypro.ads.entity.AdsImage;
import ru.skypro.ads.exception.AdsNotFoundException;
import ru.skypro.ads.repository.AdsImageRepository;
import ru.skypro.ads.repository.AdsRepository;
import ru.skypro.ads.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Transactional
public class ImageController {
    private final ImageService imageService;
    private final AdsImageRepository adsImageRepository;
    private final AdsRepository adsRepository;

    @Value("${ads.image.dir.path}")
    private String adsImageDir;

    public ImageController(ImageService imageService, AdsImageRepository adsImageRepository,
                           AdsRepository adsRepository) {
        this.imageService = imageService;
        this.adsImageRepository = adsImageRepository;
        this.adsRepository = adsRepository;
    }

    /**
     * Получить рекламное изображение с диска по его идентификатору.
     *
     * @param adsId    идентификатор объявления
     * @param response ответное изображение объявления, отправленное во внешний интерфейс
     * @throws IOException выдает исключение {@link IOException}
     */
    @GetMapping("/ads-image/{adsId}")
    public void getAdImageFromDisk(@PathVariable Integer adsId, HttpServletResponse response) throws IOException {
        Ads ads = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);

        Optional<AdsImage> imageDetails = adsImageRepository.findByAds(ads);
        if (imageDetails.isEmpty()) {
            return;
        }

        Path filePath = Path.of(adsImageDir, ads.getUser().getId() + "-" + ads.getId() + "."
                + imageDetails.get().getFileExtension());
        imageService.getImageFromDisk(response, filePath, imageDetails.get());
    }
}
