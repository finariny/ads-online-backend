package ru.skypro.ads.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.ads.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Transactional
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Получить рекламное изображение с диска по его идентификатору.
     *
     * @param id       идентификатор изображения
     * @param response ответное изображение объявления, отправленное во внешний интерфейс
     * @throws IOException выдает исключение {@link IOException}
     */
    @GetMapping("/image/{id}")
    public void transferImageToResponse(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        imageService.transferImageToResponse(id, response);
    }
}
