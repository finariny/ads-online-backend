package ru.skypro.ads.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.AdsDto;
import ru.skypro.ads.service.AdsService;

import java.util.Collection;

@Service
public class AdsServiceImpl implements AdsService {


    @Override
    public Collection<AdsDto> getAllAds() {
        return null;
    }

    @Override
    public AdsDto save(Object ads, String name, MultipartFile image) {
        return null;
    }
    @Override
    public AdsDto getAds(Integer id) {
//        AdsDto ads = adsRepository.findById(id).orElseThrow();
//        return ads;
        return null;
    }


}