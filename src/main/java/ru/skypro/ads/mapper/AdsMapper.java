package ru.skypro.ads.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.ads.dto.AdsDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdsMapper {

    //Чтобы в сервисах удобно взаимодействовать с этим маппером не создавая лишних экземпляров
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    AdsDto /*-AdsDto Dto*/ adsToAdsDto (ru.skypro.ads.entity.Ads ads);

    ru.skypro.ads.entity.Ads adsDtoToAds(AdsDto adsDto);

    List<AdsDto> listAdsToAdsDto(List<ru.skypro.ads.entity.Ads> adsList);

}
