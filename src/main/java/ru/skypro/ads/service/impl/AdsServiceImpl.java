package ru.skypro.ads.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.Ads;
import ru.skypro.ads.dto.CreateAds;
import ru.skypro.ads.exception.AdsNotFoundException;
import ru.skypro.ads.mapper.AdsMapper;
import ru.skypro.ads.repository.AdsRepository;
import ru.skypro.ads.dto.ResponseWrapperAds;
import ru.skypro.ads.service.AdsService;

import java.util.Collection;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {
    @Autowired
    private AdsRepository adsRepository;

    /**
     * Получает все объявления
     *
     * @return коллекция всех AdsDto сразу конвертируя маппером из репозитория обычных Ads
     */
    @Override
    public Collection<Ads> getAllAds() {
        return AdsMapper.INSTANCE.listAdsToAdsDto(adsRepository.findAll());
    }

    /**
     * Добавляет объявление
     *
     * @param ad    объект {@link Ads}
     * @param image объект {@link MultipartFile}
     * @return объект {@link Ads}
     */
    @Override
    public Ads saveAd(CreateAds ads, MultipartFile image) {
        ru.skypro.ads.entity.Ads saveAds = new ru.skypro.ads.entity.Ads();
        saveAds.setTitle(ads.getTitle());
        saveAds.setDescription(ads.getDescription());
        saveAds.setPrice(ads.getPrice());
        saveAds.setImage(image.getName()); // Todo продумать работу с image
        adsRepository.save(saveAds);
        return AdsMapper.INSTANCE.adsToAdsDto(saveAds);
    }

    /**
     * Получает информацию об объявлении
     *
     * @param id идентификатор объявления
     * @return объект {@link Ads}
     */
    @Override
    public Ads getAd(Integer id) {
        ru.skypro.ads.entity.Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        return AdsMapper.INSTANCE.adsToAdsDto(ads);
    }

    /**
     * Удаляет объявление
     *
     * @param id идентификатор объявления
     * @return <code>true</code> если объявление удалено, <code>false</code> в случае неудачи
     */
    @Override
    public boolean removeAd(int id) {
        return false;
    }

    /**
     * Обновляет информацию об объявлении
     *
     * @param id        идентификатор объявления
     * @param createAds новая информация об объявлении
     * @return объект {@link Ads}
     */
    @Override
    public Ads updateAds(int id, CreateAds createAds) {
        return null;
    }

    /**
     * Получает данные об объявлениях пользователя
     *
     * @param auth данные о текущем пользователе
     * @return данные об объявлениях пользователя в виде дто-объекта {@link ResponseWrapperAds}
     */
    @Override
    public ResponseWrapperAds getAdsMe(Authentication auth) {
        return null;
    }

    /**
     * Обновляет картинку объявления
     *
     * @param id   идентификатор объявления
     * @param image новая картинка
     * @return добавленная картинка
     */
    @Override
    public byte[] updateImage(int id, MultipartFile image) {
        return new byte[0];
    }
  
}
