package ru.skypro.ads.mapper;

import org.mapstruct.*;
import ru.skypro.ads.dto.CommentDto;
import ru.skypro.ads.dto.CreateCommentDto;
import ru.skypro.ads.dto.ResponseWrapperCommentDto;
import ru.skypro.ads.entity.Comment;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommentMapper {


    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "user.id")
    Comment commentDtoToComment(CommentDto commentDto);


    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToLong")
    @Mapping(source = "user.image.url", target = "authorImage")
    CommentDto commentToCommentDto(Comment comment);


    void updateCommentFromCommentDto(CreateCommentDto createCommentDto, @MappingTarget Comment comment);


    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "longToLocalDateTime")
    void updateCommentFromCommentDto(CommentDto commentDto, @MappingTarget Comment comment);


    ResponseWrapperCommentDto listCommentToCommentDto(int count, Collection<Comment> results);


    @Named("localDateTimeToLong")
    default Long localDateTimeToLong(LocalDateTime dateTime) {
        return dateTime.toInstant(ZonedDateTime.now().getOffset())
                .toEpochMilli();
    }

    @Named("longToLocalDateTime")
    default LocalDateTime longToLocalDateTime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);
    }
}
