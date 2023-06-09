package ru.skypro.ads.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.skypro.ads.dto.AdsDto;
import ru.skypro.ads.dto.CreateAdsDto;
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
    AdsDto adsToAdsDto(Ads ads);

    /**
     * Сопоставляет объект {@link AdsDto} в объект {@link Ads}
     *
     * @param adsDto объект {@link AdsDto}
     * @return объект {@link Ads}
     */
    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "user.id")
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

    /**
     * Сопоставляет список объектов {@link Ads} и их количество в объект {@link ResponseWrapperAdsDto}
     *
     * @param count   количество элементов в списке
     * @param results список объектов {@link Ads}
     * @return объект {@link ResponseWrapperAdsDto}
     */
    ResponseWrapperAdsDto listAdsToAdsDto(int count, List<Ads> results);
}
