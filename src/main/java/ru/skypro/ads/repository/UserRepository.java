package ru.skypro.ads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.ads.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user_ order by id", nativeQuery = true)
    List<User> findAllUsers();

    Optional<User> getUserByEmailIgnoreCase(String username);

    Optional<User> findUserByEmail(String email);
}
