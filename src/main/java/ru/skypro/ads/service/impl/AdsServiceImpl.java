package ru.skypro.ads.service.impl;

import org.springframework.beans.factory.annotation.Value;
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
import ru.skypro.ads.service.ImageService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Value("${ads.image.dir.path}")
    private String adsImageDir;

    public AdsServiceImpl(AdsRepository adsRepository, UserRepository userRepository, ImageService imageService) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
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
        User user = userRepository.findUserByEmail(authentication.getName());
        if (user == null) {
            throw new UserNotFoundException();
        }
        List<AdsDto> ads = new ArrayList<>();
        for (Ads ad : adsRepository.findByUser(user)) {
            AdsDto adsDto = AdsMapper.INSTANCE.adsToAdsDto(ad);
            ads.add(adsDto);
        }
        return new ResponseWrapperAdsDto(ads.size(), ads);
    }

    /**
     * Обновляет картинку объявления
     *
     * @param id    идентификатор объявления
     * @param image новая картинка
     * @return добавленная картинка
     */
    @Override
    public boolean updateImage(int id, MultipartFile image, Authentication authentication) throws IOException {
        return true;
    }

//    private Path getFilePath(Ads ads, MultipartFile image) {
//        return Path.of(adsImageDir, ads.getUser().getId() + "-" + ads.getId() + "." + imageService.getExtension(image.getOriginalFilename()));
//    }

}
