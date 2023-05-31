package ru.skypro.ads.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.ads.dto.AdsDto;
import ru.skypro.ads.dto.CreateAdsDto;
import ru.skypro.ads.entity.Ads;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdsMapper {

    //Чтобы в сервисах удобно взаимодействовать с этим маппером не создавая лишних экземпляров
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    AdsDto adsToAdsDto (Ads ads);

    Ads adsDtoToAds(AdsDto adsDto);
    Ads adsDtoToAds(CreateAdsDto createAdsDto);



    List<AdsDto> listAdsToAdsDto(List<Ads> adsList);

}
