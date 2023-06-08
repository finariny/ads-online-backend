package ru.skypro.ads.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.ads.dto.UserDto;
import ru.skypro.ads.entity.User;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);
//    @BeanMapping (nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
//    @Mapping(target = "id", source = "id")
//    void userDtoPartialUpdateUser(UserDto userDto, @MappingTarget User user); // для обновления юзера в
//
//   List<UserDto> userEntitiesToUserDto(List<User> usersList); может еще это понадобится?

}
