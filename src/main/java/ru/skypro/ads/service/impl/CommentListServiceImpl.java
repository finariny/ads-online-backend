package ru.skypro.ads.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.ads.dto.Comment;
import ru.skypro.ads.dto.CommentCreate;
import ru.skypro.ads.dto.CommentList;
import ru.skypro.ads.service.CommentListService;

import java.util.List;

@Service
public class CommentListServiceImpl implements CommentListService {

    private CommentList commentList;

    @Override
    public CommentList getComments(int id) {
        return commentList;
    }

    @Override
    public Comment addComment(int id, CommentCreate text) {
        Comment comment = new Comment();
        return comment;
    }

    @Override
    public boolean deleteComment(int adId, int commentId) {
        return false;
    }

    @Override
    public Comment patchComment(Integer adId, Integer commentId, Comment comment) {
        return null;
    }
}
