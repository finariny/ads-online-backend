package ru.skypro.ads.mapper;

import org.mapstruct.*;
import ru.skypro.ads.dto.AdsDto;
import ru.skypro.ads.dto.CreateAdsDto;
import ru.skypro.ads.dto.FullAdsDto;
import ru.skypro.ads.dto.ResponseWrapperAdsDto;
import ru.skypro.ads.entity.Ads;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdsMapper {

    /**
     * Сопоставляет объект {@link Ads} в объект {@link AdsDto}
     *
     * @param ads объект {@link Ads}
     * @return объект {@link AdsDto}
     */
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    @Mapping(target = "image", expression = "java(ads.getImage() != null ? ads.getImage().getUrl() : \"\")")
    AdsDto adsToAdsDto(Ads ads);

    /**
     * Сопоставляет объект {@link AdsDto} в объект {@link Ads}
     *
     * @param adsDto объект {@link AdsDto}
     * @return объект {@link Ads}
     */
    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "user.id")
    @Mapping(target = "image", expression = "java(new ru.skypro.ads.entity.Image())")
    Ads adsDtoToAds(AdsDto adsDto);

    /**
     * Сопоставляет объект {@link CreateAdsDto} в объект {@link Ads}
     *
     * @param createAdsDto объект {@link CreateAdsDto}
     * @return объект {@link Ads}
     */
    Ads adsDtoToAds(CreateAdsDto createAdsDto);

    /**
     * Обновляет объект {@link Ads} из объекта {@link CreateAdsDto}
     *
     * @param createAdsDto объект {@link CreateAdsDto}
     * @param ads          объект {@link Ads}
     */
    void updateAdsFromCreateAdsDto(CreateAdsDto createAdsDto, @MappingTarget Ads ads);
    //Вопрос по этому методу? Он не возвращает.

    /**
     * Сопоставляет список объектов {@link Ads} и их количество в объект {@link ResponseWrapperAdsDto}
     *
     * @param count   количество элементов в списке
     * @param results список объектов {@link Ads}
     * @return объект {@link ResponseWrapperAdsDto}
     */
    ResponseWrapperAdsDto listAdsToAdsDto(int count, List<Ads> results);

    /**
     * Преобразует ads в обьект с полной информацией об объявлении и авторе
     *
     * @param ads сущность объявления
     * @return FullAdsDto
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "image", expression = "java(ads.getImage() != null ? ads.getImage().getUrl() : \"\")")
    @Mapping(target = "pk", source = "id")
    FullAdsDto toFullAdsDto(Ads ads);
}
