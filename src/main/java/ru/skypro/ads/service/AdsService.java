package ru.skypro.ads.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.AdsDto;

import java.util.Collection;

public interface AdsService {


    Collection<AdsDto> getAllAds();

    AdsDto save(Object ads, String name, MultipartFile image);

    AdsDto getAds(Integer id);
}
