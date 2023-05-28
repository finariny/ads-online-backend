package ru.skypro.ads.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.ads.dto.Comment;
import ru.skypro.ads.dto.CreateComment;
import ru.skypro.ads.dto.ResponseWrapperComment;
import ru.skypro.ads.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private ResponseWrapperComment responseWrapperComment;

    @Override
    public ResponseWrapperComment getComments(int id) {
        return responseWrapperComment;
    }

    @Override
    public Comment addComment(int id, CreateComment text) {
        return new Comment();
    }

    @Override
    public boolean deleteComment(int adId, int commentId) {
        return false;
    }

    @Override
    public Comment updateComment(Integer adId, Integer commentId, Comment comment) {
        return null;
    }
}
