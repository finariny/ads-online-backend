package ru.skypro.ads.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.skypro.ads.dto.UserDto;
import ru.skypro.ads.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);
//    @BeanMapping (nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
//    @Mapping(target = "id", source = "id")
//    void userDtoPartialUpdateUser(UserDto userDto, @MappingTarget User user); // для обновления юзера в
//
//   List<UserDto> userEntitiesToUserDto(List<User> usersList); может еще это понадобится?

    User userDtoToUser(UserDto userDto);
}
