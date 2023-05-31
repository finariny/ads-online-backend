package ru.skypro.ads.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.ads.dto.CommentDto;
import ru.skypro.ads.dto.CreateCommentDto;
import ru.skypro.ads.dto.ResponseWrapperCommentDto;
import ru.skypro.ads.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private ResponseWrapperCommentDto responseWrapperCommentDto;

    @Override
    public ResponseWrapperCommentDto getComments(int id) {
        return responseWrapperCommentDto;
    }

    @Override
    public CommentDto addComment(int id, CreateCommentDto text) {
        return new CommentDto();
    }

    @Override
    public boolean deleteComment(int adId, int commentId) {
        return false;
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CommentDto commentDto) {
        return null;
    }
}
