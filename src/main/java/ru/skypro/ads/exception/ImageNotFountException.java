package ru.skypro.ads.exception;

import liquibase.pro.packaged.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageNotFountException extends RuntimeException {
    public ImageNotFountException() {
        super("Image not found");
    }

    public ImageNotFountException(String message) {
        super(message);
    }
}
