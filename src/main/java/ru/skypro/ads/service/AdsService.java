package ru.skypro.ads.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.AdsDto;
import ru.skypro.ads.dto.CreateAdsDto;
import ru.skypro.ads.dto.FullAdsDto;
import ru.skypro.ads.dto.ResponseWrapperAdsDto;

public interface AdsService {

    /**
     * Получает все объявления
     *
     * @return объект {@link ResponseWrapperAdsDto}
     */
    ResponseWrapperAdsDto getAllAds();

    /**
     * Добавляет объявление
     *
     * @param ad    объект {@link CreateAdsDto}
     * @param image объект {@link MultipartFile}
     * @return объект {@link AdsDto}
     */
    AdsDto saveAd(CreateAdsDto ad, String name, MultipartFile image);

    /**
     * Получает информацию об объявлении
     *
     * @param id идентификатор объявления
     * @return объект {@link AdsDto}
     */
    FullAdsDto getAd(Integer id);

    /**
     * Удаляет объявление
     *
     * @param id идентификатор объявления
     * @return <code>true</code> если объявление удалено, <code>false</code> в случае неудачи
     */
    boolean removeAd(String name, int id);

    /**
     * Обновляет информацию об объявлении
     *
     * @param id           идентификатор объявления
     * @param createAdsDto новая информация об объявлении
     * @return объект {@link AdsDto}
     */
    AdsDto updateAds(int id, CreateAdsDto createAdsDto);

    /**
     * Возвращает объявления авторизованного пользователя
     *
     * @param authentication данные о текущем пользователе
     * @return список объявлений
     */
    ResponseWrapperAdsDto getAdsMe(Authentication authentication);

    /**
     * Обновляет картинку объявления
     *
     * @param id    идентификатор объявления
     * @param image новая картинка
     * @return добавленная картинка
     */
    boolean updateImage(int id, MultipartFile image);

    boolean isThisUser(String email, int id);
}
