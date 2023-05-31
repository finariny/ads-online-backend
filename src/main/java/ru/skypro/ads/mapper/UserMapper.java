package ru.skypro.ads.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.ads.dto.UserDto;
import ru.skypro.ads.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

}
