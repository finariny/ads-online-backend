package ru.skypro.ads.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.Ads;
import ru.skypro.ads.dto.CreateAds;
import ru.skypro.ads.dto.ResponseWrapperAds;

import java.util.Collection;

public interface AdsService {

    /**
     * Получение всех объявлений
     *
     * @return коллекция всех объектов {@link Ads}
     */
    Collection<Ads> getAllAds();

    /**
     * Добавление объявления
     *
     * @param ads
     * @param image объект {@link MultipartFile}
     * @return объект {@link Ads}
     */
    Ads save(Object ads, MultipartFile image);

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
     * @return <code>true</code> если объявление обновлено, <code>false</code> в случае неудачи
     */
    boolean updateAd(int id, CreateAds createAds);

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
     * @param id первичный ключ объявления
     * @param file новая картинка
     * @return добавленная картинка
     */
    byte[] updateImage(Integer id, MultipartFile file);








}
