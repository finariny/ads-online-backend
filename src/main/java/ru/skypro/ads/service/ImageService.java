package ru.skypro.ads.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.entity.Image;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ImageService {
    void transferImageToResponse(Integer imageId, HttpServletResponse response) throws IOException;

    Image saveImageFile(MultipartFile imageFile) throws IOException;
}

