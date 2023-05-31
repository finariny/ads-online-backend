package ru.skypro.ads.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperAdsDto {
    private Integer count;
    private List<AdsDto> results;
}
