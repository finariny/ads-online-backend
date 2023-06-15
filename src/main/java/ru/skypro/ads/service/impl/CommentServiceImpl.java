package ru.skypro.ads.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.ads.dto.CommentDto;
import ru.skypro.ads.dto.CreateCommentDto;
import ru.skypro.ads.dto.ResponseWrapperCommentDto;
import ru.skypro.ads.entity.Comment;
import ru.skypro.ads.entity.User;
import ru.skypro.ads.exception.AdsNotFoundException;
import ru.skypro.ads.exception.CommentNotFoundException;
import ru.skypro.ads.mapper.CommentMapper;
import ru.skypro.ads.repository.AdsRepository;
import ru.skypro.ads.repository.CommentRepository;
import ru.skypro.ads.repository.UserRepository;
import ru.skypro.ads.service.CommentService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private AdsRepository adsRepository;
    private CommentMapper commentMapper;
    private UserRepository userRepository;

    private int idComment = 0;

    public CommentServiceImpl(CommentRepository commentRepository, AdsRepository adsRepository, CommentMapper commentMapper, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseWrapperCommentDto getComments(int id) {
        List<Comment> comments = commentRepository.findAllByAdsId(id);
        return commentMapper.listCommentToCommentDto(comments.size(), comments);
    }

    @Override
    public CommentDto addComment(int id, CreateCommentDto text) {
        User user = userRepository
                .findUserByEmail(SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName())
                .orElseThrow();
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setId(++idComment);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setAds(adsRepository.findById(id).orElseThrow());
        comment.setText(text.getText());
        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public boolean deleteComment(int adId, int commentId, Authentication authentication) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        User user = userRepository
                .findUserByEmail(SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName())
                .orElseThrow();
        if (comment.getUser().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
            if (comment.getAds().getId() != adId) {
                throw new AdsNotFoundException();
            }
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CommentDto commentDto, Authentication authentication) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        User user = userRepository.findUserByEmail(authentication.getName()).orElseThrow();
        if (comment.getUser().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
            if (comment.getAds().getId() != adId) {
                throw new AdsNotFoundException();
            }
            comment.setText(commentDto.getText());
            commentRepository.save(comment);
            return commentMapper.commentToCommentDto(comment);
        }
        return commentDto;
    }

}


