package ru.skypro.ads.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.entity.Image;
import ru.skypro.ads.exception.ImageNotFountException;
import ru.skypro.ads.repository.ImageRepository;
import ru.skypro.ads.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${ads.image.dir.path}")
    private String imageDir;

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Получение изображения с диска
     *
     * @param imageId  идентификатор сущности изображения
     * @param response ответ сервера
     * @throws {@link IOException}
     */
    @Override
    public void transferImageToResponse(Integer imageId, HttpServletResponse response) throws IOException {
        Image imageDetails = imageRepository.findById(imageId).orElseThrow(ImageNotFountException::new);
        try (InputStream is = Files.newInputStream(imageDetails.getPath());
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(imageDetails.getMediaType());
            response.setContentLength((int) imageDetails.getFileSize());
            is.transferTo(os);
        }
    }

    @Override
    public Image saveImageFile(MultipartFile imageFile) throws IOException {
        Path filePath = createPathFromFile(imageFile);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = imageFile.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
        return saveImageDetails(imageFile, filePath);
    }

    private Path createPathFromFile(MultipartFile imageFile) {
        Path path = Path.of(String.format("%s/%s", imageDir, imageFile.getOriginalFilename()));
        if (Files.exists(path)) {
            path = Path.of(String.format("%s/%s.%s", imageDir, generateFileName(),
                    getExtension(Objects.requireNonNull(imageFile.getOriginalFilename(), ""))));
        }
        return path;
    }

    private String generateFileName() {
        int length = 8;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    /**
     * Получение расширения файла изображения
     *
     * @param fileName полное имя файла
     * @return расширение файла
     */
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Image saveImageDetails(MultipartFile imageFile, Path filePath) {
        Image imageDetails = imageRepository.findByFilePath(filePath.toString()).orElse(new Image());
        imageDetails.setFilePath(filePath.toString());
        imageDetails.setFileExtension(getExtension(Objects.requireNonNull(imageFile.getOriginalFilename())));
        imageDetails.setFileSize(imageFile.getSize());
        imageDetails.setMediaType(imageFile.getContentType());
        return imageRepository.saveAndFlush(imageDetails);
    }
}
