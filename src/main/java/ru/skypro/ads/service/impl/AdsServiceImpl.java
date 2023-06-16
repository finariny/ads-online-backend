package ru.skypro.ads.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.ads.dto.AdsDto;
import ru.skypro.ads.dto.CreateAdsDto;
import ru.skypro.ads.dto.FullAdsDto;
import ru.skypro.ads.dto.ResponseWrapperAdsDto;
import ru.skypro.ads.entity.Ads;
import ru.skypro.ads.entity.Role;
import ru.skypro.ads.entity.User;
import ru.skypro.ads.exception.AdsNotFoundException;
import ru.skypro.ads.exception.UserNotFoundException;
import ru.skypro.ads.mapper.AdsMapper;
import ru.skypro.ads.repository.AdsRepository;
import ru.skypro.ads.repository.UserRepository;
import ru.skypro.ads.service.AdsService;
import ru.skypro.ads.service.ImageService;
import ru.skypro.ads.service.PermissionService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final AdsMapper adsMapper;
    private final PermissionService permissionService;
    private final ImageService imageService;

    @Value("${ads.image.dir.path}")
    private String adsImageDir;


    public AdsServiceImpl(AdsRepository adsRepository, UserRepository userRepository, AdsMapper adsMapper, PermissionService permissionService, ImageService imageService) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
        this.adsMapper = adsMapper;
        this.permissionService = permissionService;
        this.imageService = imageService;
    }

    /**
     * Получает все объявления
     *
     * @return объект {@link ResponseWrapperAdsDto}
     */
    @Override
    public ResponseWrapperAdsDto getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        log.info(adsList.toString());
        return adsMapper.listAdsToAdsDto(adsList.size(), adsList);
    }

    /**
     * Добавляет объявление
     *
     * @param ads   объект {@link AdsDto}
     * @param email e-mail пользователя
     * @param image объект {@link MultipartFile}
     * @return объект {@link AdsDto}
     */
    @Override
    public AdsDto saveAd(CreateAdsDto ads, String email, MultipartFile image) {
        Ads saveAds = adsMapper.adsDtoToAds(ads);
        saveAds.setUser(userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new));
//        saveAds.setImage(image.getName()); // Todo продумать работу с image

        saveAds = adsRepository.save(saveAds);

        Path filePath = getFilePath(saveAds, image);
        try {
            imageService.saveFileOnDisk(image, filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageService.updateAdsImageDetails(saveAds, image, filePath);

        saveAds.setImage("/" + adsImageDir + "/" + saveAds.getId());
        adsRepository.save(saveAds);

        return adsMapper.adsToAdsDto(saveAds);
    }

    /**
     * Получает информацию об объявлении
     *
     * @param id идентификатор объявления
     * @return объект {@link AdsDto}
     */
    @Override
    public FullAdsDto getAd(Integer id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        log.info(String.valueOf(ads));
        return adsMapper.toFullAdsDto(ads);
    }

    /**
     * Удаляет объявление
     *
     * @param email e-mail пользователя
     * @param id    идентификатор объявления
     * @return <code>true</code> если объявление удалено, <code>false</code> в случае неудачи
     */
    @Override
    public boolean removeAd(String email, int id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        User adOwner = ads.getUser();
        if (permissionService.isThisUserOrAdmin(email, adOwner)) {
            try {
                Files.deleteIfExists(Path.of(ads.getAdsImage().getFilePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
     * @param email        e-mail пользователя
     * @return объект {@link AdsDto}
     */
    @Override
    public AdsDto updateAds(int id, CreateAdsDto createAdsDto, String email) {
        if (adsRepository.findById(id).isPresent()) {
            Ads ads = adsRepository.findById(id).get();
            User adOwner = ads.getUser();
            if (permissionService.isThisUserOrAdmin(email, adOwner)) {
                log.info("запустился метод updateAds.");
                adsMapper.updateAdsFromCreateAdsDto(createAdsDto, ads);
                adsRepository.save(ads);
                return adsMapper.adsToAdsDto(ads);
            }
        }
        return null;
    }

    /**
     * Получает данные об объявлениях пользователя
     *
     * @param authentication данные о текущем пользователе {@link Authentication}
     * @return данные об объявлениях пользователя в виде дто-объекта {@link ResponseWrapperAdsDto}
     */
    @Override
    public ResponseWrapperAdsDto getAdsMe(Authentication authentication) {
        User authenticatedUser = userRepository
                .findUserByEmail(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        if (authenticatedUser.getRole() == (Role.ADMIN) || authenticatedUser.getRole() == (Role.USER)) {
            log.info("Пользователь авторизован и запустился метод getAdsMe!");
            List<Ads> adsList = adsRepository.findAllByUser(authenticatedUser);
            return adsMapper.listAdsToAdsDto(adsList.size(), adsList);
        } else {
            log.info("Пользователь не авторизован. Метод getAdsMe не запустился!");
            return new ResponseWrapperAdsDto();
        }
    }

    /**
     * Обновляет картинку объявления
     *
     * @param id    идентификатор объявления
     * @param image новая картинка
     * @return <code>true</code> если картинка обновлена, <code>false</code> в случае неудачи
     */
    @Override
    public boolean updateImage(int id, MultipartFile image, String email) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        User adOwner = ads.getUser();
        if (permissionService.isThisUserOrAdmin(email, adOwner)) {
            log.info("запустился метод updateImage.");

            Path filePath = getFilePath(ads, image);
            try {
                imageService.saveFileOnDisk(image, filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            imageService.updateAdsImageDetails(ads, image, filePath);

            ads.setImage("/" + adsImageDir + "/" + ads.getId());
            adsRepository.save(ads);

            return true;
        }

        return false;
    }

    private Path getFilePath(Ads ads, MultipartFile image) {
        return Path.of(adsImageDir, ads.getUser().getId() + "-" + ads.getId() + "."
                + imageService.getExtension(image.getOriginalFilename()));
    }
}
