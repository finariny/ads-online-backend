package ru.skypro.ads.service;

import ru.skypro.ads.dto.Comment;
import ru.skypro.ads.dto.CommentCreate;
import ru.skypro.ads.dto.CommentList;

import java.util.List;

public interface CommentListService {
   CommentList getComments(int id);

   Comment addComment(int id, CommentCreate text);

   boolean deleteComment(int adId, int commentId);

   Comment patchComment(Integer adId, Integer commentId, Comment comment);

}
