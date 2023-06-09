package ru.skypro.ads.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.ads.dto.CommentDto;
import ru.skypro.ads.dto.CreateCommentDto;
import ru.skypro.ads.dto.ResponseWrapperCommentDto;
import ru.skypro.ads.entity.Comment;
import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    @Mapping(target = "createdAt", source = "createdAt")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "user.id")
    Comment commentDtoToComment(CommentDto commentDto);

    void updateCommentFromCommentDto(CreateCommentDto createCommentDto, @MappingTarget Comment comment);

    @Mapping(target = "createdAt", source = "createdAt")
    void updateCommentFromCommentDto(CommentDto commentDto, @MappingTarget Comment comment);

    ResponseWrapperCommentDto listCommentToCommentDto(int count, Collection<Comment> results);

}
