package ru.skypro.ads.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.ads.dto.UserDto;
import ru.skypro.ads.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    String USER_AVATAR = "/users/avatar/";

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserDto userToUserDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User userDtoPartialUpdateUser(UserDto userDto, @MappingTarget User user); // для обновления юзера

//   List<UserDto> userEntitiesToUserDto(List<User> usersList); может еще это понадобится?

    User userDtoToUser(UserDto userDto);
}
