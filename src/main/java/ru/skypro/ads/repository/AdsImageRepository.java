package ru.skypro.ads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.ads.entity.Ads;
import ru.skypro.ads.entity.AdsImage;

import java.util.Optional;

@Repository
public interface AdsImageRepository extends JpaRepository<AdsImage, Integer> {
    Optional<AdsImage> findByAds(Ads ads);
}
