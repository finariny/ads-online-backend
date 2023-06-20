package ru.skypro.ads.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.ads.dto.UserDto;
import ru.skypro.ads.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    /**
     * Сопоставляет объект {@link User} в объект {@link UserDto}
     *
     * @param user объект {@link User}
     * @return объект {@link UserDto}
     */
    @Mapping(target = "image", expression = "java(user.getImage() != null ? user.getImage().getUrl() : \"\")")
    UserDto userToUserDto(User user);
}
