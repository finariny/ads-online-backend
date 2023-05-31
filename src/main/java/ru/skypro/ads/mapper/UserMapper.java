package ru.skypro.ads.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.ads.dto.UserDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto /*- это UserDto DTO*/ userToUserDto(ru.skypro.ads.entity.User user);

}
