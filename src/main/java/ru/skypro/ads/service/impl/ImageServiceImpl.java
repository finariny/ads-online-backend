package ru.skypro.ads.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.entity.Ads;
import ru.skypro.ads.entity.AdsImage;
import ru.skypro.ads.service.ImageInterface;
import ru.skypro.ads.repository.AdsImageRepository;
import ru.skypro.ads.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class ImageServiceImpl implements ImageService {

    private final AdsImageRepository adsImageRepository;

    public ImageServiceImpl(AdsImageRepository adsImageRepository) {
        this.adsImageRepository = adsImageRepository;
    }

    /**
     * Получение изображения с диска
     *
     * @param response     ответ сервера
     * @param filePath     путь к файлу изображения объявления на диске (откуда оно должно быть получено)
     * @param imageDetails image детали
     */
    @Override
    public void getImageFromDisk(HttpServletResponse response, Path filePath, ImageInterface imageDetails)
            throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(imageDetails.getMediaType());
            response.setContentLength((int) imageDetails.getFileSize());
            is.transferTo(os);
        }
    }

    /**
     * Обновление сведений об изображении.
     *
     * @param ads      объявление, которое должно быть обновлено
     * @param image    изображение объявления
     * @param filePath путь к файлу изображения объявления на диске (где оно должно быть сохранено)
     */
    @Override
    public void updateAdsImageDetails(Ads ads, MultipartFile image, Path filePath) {
        AdsImage imageDetails = adsImageRepository.findByAds(ads).orElse(new AdsImage());
        imageDetails.setFilePath(filePath.toString());
        imageDetails.setFileExtension(getExtension(Objects.requireNonNull(image.getOriginalFilename())));
        imageDetails.setFileSize(image.getSize());
        imageDetails.setMediaType(image.getContentType());
        imageDetails.setAds(ads);
        adsImageRepository.saveAndFlush(imageDetails);
    }

    /**
     * Получение расширения файла изображения
     *
     * @param fileName полное имя файла
     * @return расширение файла
     */
    @Override
    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * Запись файла изображения на диск
     *
     * @param image    изображение объявления
     * @param filePath путь к файлу изображения объявления на диске (где оно должно быть сохранено)
     */
    @Override
    public void saveFileOnDisk(MultipartFile image, Path filePath) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
    }
}
