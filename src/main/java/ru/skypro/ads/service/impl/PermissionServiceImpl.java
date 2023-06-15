package ru.skypro.ads.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.skypro.ads.entity.Role;
import ru.skypro.ads.entity.User;
import ru.skypro.ads.service.PermissionService;

@Log4j2
@Service
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean isThisUser(String email, User ownerAds) {
        if (email.equals(ownerAds.getEmail())) {
            log.info("Владелец прошел!");
            return true;
        } else {
            log.info("Владелец не прошел!");
            return false;
        }
    }

    @Override
    public boolean isAdmin(User user) {
        if (user.getRole() == (Role.ADMIN)) {
            log.info("Админ прошел!");
            return true;
        } else {
            log.info("Админ не прошел!");
            return false;
        }
    }

    @Override
    public boolean isThisUserOrAdmin(String email, User ownerAds) {
        return isThisUser(email, ownerAds) || isAdmin(ownerAds);
    }
}
