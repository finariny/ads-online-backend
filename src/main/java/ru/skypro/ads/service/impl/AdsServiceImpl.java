package ru.skypro.ads.service.impl;

import org.springframework.security.core.Authentication;
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

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final UserRepository userRepository;

    public AdsServiceImpl(AdsRepository adsRepository, UserRepository userRepository) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
    }

    /**
     * Получает все объявления
     *
     * @return объект {@link ResponseWrapperAdsDto}
     */
    @Override
    public ResponseWrapperAdsDto getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        return AdsMapper.INSTANCE.listAdsToAdsDto(adsList.size(), adsList);
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
        if (adsRepository.existsById(id)) {
            adsRepository.deleteById(id);
            return true;
        }
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
        if (adsRepository.findById(id).isPresent()) {
            Ads ads = adsRepository.findById(id).get();
            AdsMapper.INSTANCE.updateAdsFromCreateAdsDto(createAdsDto, ads);
            adsRepository.save(ads);
            return AdsMapper.INSTANCE.adsToAdsDto(ads);
        }
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
        User user = userRepository.findUserByEmail(authentication.getName());
        if (user == null) {
            throw new UserNotFoundException();
        }
        List<Ads> adsList = adsRepository.findByUser(user);
        return AdsMapper.INSTANCE.listAdsToAdsDto(adsList.size(), adsList);
    }

    /**
     * Обновляет картинку объявления
     *
     * @param id    идентификатор объявления
     * @param image новая картинка
     * @return добавленная картинка
     */
    @Override
    public boolean updateImage(int id, MultipartFile image) {
        return true;
    }

}
