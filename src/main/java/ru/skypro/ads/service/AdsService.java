package ru.skypro.ads.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.Ads;
import ru.skypro.ads.dto.CreateAds;
import ru.skypro.ads.dto.ResponseWrapperAds;

import java.util.Collection;

public interface AdsService {

    /**
     * Получает все объявления
     *
     * @return коллекция всех объектов {@link Ads}
     */
    Collection<Ads> getAllAds();

    /**
     * Добавляет объявление
     *
     * @param ad    объект {@link CreateAds}
     * @param image объект {@link MultipartFile}
     * @return объект {@link Ads}
     */
    Ads saveAd(CreateAds ad, MultipartFile image);

    /**
     * Получает информацию об объявлении
     *
     * @param id идентификатор объявления
     * @return объект {@link Ads}
     */
    Ads getAd(Integer id);

    /**
     * Удаляет объявление
     *
     * @param id идентификатор объявления
     * @return <code>true</code> если объявление удалено, <code>false</code> в случае неудачи
     */
    boolean deleteAd(int id);

    /**
     * Обновляет информацию об объявлении
     *
     * @param id        идентификатор объявления
     * @param createAds новая информация об объявлении
     * @return объект {@link Ads}
     */
    Ads updateAd(int id, CreateAds createAds);

    /**
     * Возвращает объявления авторизованного пользователя
     *
     * @param auth
     * @return список объявлений
     */
    ResponseWrapperAds getAdsMe(Authentication auth);

    /**
     * Обновляет картинку объявления
     *
     * @param id   идентификатор объявления
     * @param image новая картинка
     * @return добавленная картинка
     */
    byte[] updateImage(int id, MultipartFile image);

}
