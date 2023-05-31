package ru.skypro.ads.mapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.ads.dto.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User /*- это User DTO*/ userToUserDto(ru.skypro.ads.entity.User user);

}
