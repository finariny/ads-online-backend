package ru.skypro.ads.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.Ads;
import ru.skypro.ads.dto.CreateAds;
import ru.skypro.ads.repository.AdsRepository;
import ru.skypro.ads.dto.ResponseWrapperAds;
import ru.skypro.ads.service.AdsService;

import java.util.Collection;

@Service
public class AdsServiceImpl implements AdsService {
    @Autowired
    private AdsRepository adsRepository;

    /**
     * Получение всех объявлений
     *
     * @return коллекция всех объектов {@link Ads}
     */
    @Override
    public Collection<Ads> getAllAds() {
        return null;
    }

    /**
     * Добавление объявления
     *
     * @param ads
     * @param image объект {@link MultipartFile}
     * @return объект {@link Ads}
     */
    @Override
    public Ads save(Object ads, MultipartFile image) {
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
//        AdsDto ads = adsRepository.findById(id).orElseThrow();
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
    public boolean deleteAd(int id) {
        return false;
    }

    /**
     * Обновляет информацию об объявлении
     *
     * @param id        идентификатор объявления
     * @param createAds новая информация об объявлении
     * @return <code>true</code> если объявление обновлено, <code>false</code> в случае неудачи
     */
    @Override
    public boolean updateAd(int id, CreateAds createAds) {
        return false;
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
     * @param id первичный ключ объявления
     * @param file новая картинка
     * @return добавленная картинка
     */
    @Override
    public byte[] updateImage(Integer id, MultipartFile file) {
        return new byte[0];
    }


}