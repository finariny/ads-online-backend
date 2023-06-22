package ru.skypro.ads.mapper;

import org.mapstruct.*;
import ru.skypro.ads.dto.CommentDto;
import ru.skypro.ads.dto.CreateCommentDto;
import ru.skypro.ads.dto.ResponseWrapperCommentDto;
import ru.skypro.ads.entity.Comment;

import java.time.*;
import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "localDateTimeToLong")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "user.id")
    Comment commentDtoToComment(CommentDto commentDto);

    void updateCommentFromCommentDto(CreateCommentDto createCommentDto, @MappingTarget Comment comment);

    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "longToLocalDateTime")
    void updateCommentFromCommentDto(CommentDto commentDto, @MappingTarget Comment comment);

    ResponseWrapperCommentDto listCommentToCommentDto(int count, Collection<Comment> results);

    @Named("localDateTimeToLong")
    default Long localDateTimeToLong(LocalDateTime dateTime) {
        dateTime = LocalDateTime.now();
        return dateTime.toInstant(ZonedDateTime.now().getOffset())
                .toEpochMilli();
    }

    @Named("longToLocalDateTime")
    default LocalDateTime longToLocalDateTime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);
    }
}
