package ru.skypro.ads.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.Ads;
import ru.skypro.ads.dto.CreateAds;
import ru.skypro.ads.dto.ResponseWrapperAds;
import ru.skypro.ads.service.AdsService;

import java.util.Collection;

@Service
public class AdsServiceImpl implements AdsService {

    /**
     * Получает все объявления
     *
     * @return коллекция всех объектов {@link Ads}
     */
    @Override
    public Collection<Ads> getAllAds() {
        return null;
    }

    /**
     * Добавляет объявление
     *
     * @param ad    объект {@link Ads}
     * @param image объект {@link MultipartFile}
     * @return объект {@link Ads}
     */
    @Override
    public Ads saveAd(CreateAds ad, MultipartFile image) {
        return null;
    }

    /**
     * Получает информацию об объявлении
     *
     * @param id идентификатор объявления
     * @return объект {@link Ads}
     */
    @Override
    public Ads getAd(Integer id) {
//        Ads ads = adsRepository.findById(id).orElseThrow();
//        return ads;
        return null;
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
