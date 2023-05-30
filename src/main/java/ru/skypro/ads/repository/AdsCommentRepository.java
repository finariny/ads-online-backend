package ru.skypro.ads.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.ads.entity.AdsComment;

@Repository
public interface AdsCommentRepository extends JpaRepository<AdsComment, Integer> {

}