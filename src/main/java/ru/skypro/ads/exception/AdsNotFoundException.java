package ru.skypro.ads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdsNotFoundException extends RuntimeException {

    public AdsNotFoundException() {
        super("AdsDto not found");
    }
}
