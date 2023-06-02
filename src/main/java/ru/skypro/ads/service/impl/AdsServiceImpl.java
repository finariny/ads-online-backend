package ru.skypro.ads.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.AdsDto;
import ru.skypro.ads.dto.CreateAdsDto;
import ru.skypro.ads.entity.Ads;
import ru.skypro.ads.entity.User;
import ru.skypro.ads.exception.AdsNotFoundException;
import ru.skypro.ads.exception.UserNotFoundException;
import ru.skypro.ads.mapper.AdsMapper;
import ru.skypro.ads.repository.AdsRepository;
import ru.skypro.ads.dto.ResponseWrapperAdsDto;
import ru.skypro.ads.repository.UserRepository;
import ru.skypro.ads.service.AdsService;

import java.util.Collection;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {
    @Autowired
    private AdsRepository adsRepository;
    private UserRepository userRepository;

    public AdsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Получает все объявления
     *
     * @return коллекция всех AdsDto сразу конвертируя маппером из репозитория обычных AdsDto
     */
    @Override
    public Collection<AdsDto> getAllAds() {
        return AdsMapper.INSTANCE.listAdsToAdsDto(adsRepository.findAll());
    }

    /**
     * Добавляет объявление
     *
     * @param ads   объект {@link AdsDto}
     * @param image объект {@link MultipartFile}
     * @return объект {@link AdsDto}
     */
    @Override
    public AdsDto saveAd(CreateAdsDto ads, MultipartFile image) {
        Ads saveAds = AdsMapper.INSTANCE.adsDtoToAds(ads);
        saveAds.setImage(image.getName()); // Todo продумать работу с image
        adsRepository.save(saveAds);
        return AdsMapper.INSTANCE.adsToAdsDto(saveAds);
    }

    /**
     * Получает информацию об объявлении
     *
     * @param id идентификатор объявления
     * @return объект {@link AdsDto}
     */
    @Override
    public AdsDto getAd(Integer id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
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
     * @param id           идентификатор объявления
     * @param createAdsDto новая информация об объявлении
     * @return объект {@link AdsDto}
     */
    @Override
    public AdsDto updateAds(int id, CreateAdsDto createAdsDto) {
        return null;
    }

    /**
     * Получает данные об объявлениях пользователя
     *
     * @param authentication данные о текущем пользователе
     * @return данные об объявлениях пользователя в виде дто-объекта {@link ResponseWrapperAdsDto}
     */
    @Override
    public ResponseWrapperAdsDto getAdsMe(Authentication authentication) {
        User author = userRepository.findByUsername(authentication.getName());
        if (author == null) {
            throw new UserNotFoundException();
        }
        List<AdsDto> ads = adsRepository.findByAuthor(author).stream()
                .map(ad -> AdsMapper.INSTANCE.adsToAdsDto(ad))
                .toList();
        return new ResponseWrapperAdsDto(ads.size(), ads);
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
