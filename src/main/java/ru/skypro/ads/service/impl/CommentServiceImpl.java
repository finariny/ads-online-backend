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
import ru.skypro.ads.service.PermissionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final PermissionService permissionService;

    public CommentServiceImpl(CommentRepository commentRepository, AdsRepository adsRepository, CommentMapper commentMapper, UserRepository userRepository, PermissionService permissionService) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
        this.permissionService = permissionService;
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
        comment.setCreatedAt(LocalDateTime.now());
        comment.setAds(adsRepository.findById(id).orElseThrow());
        comment.setText(text.getText());
        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public boolean deleteComment(int adId, int commentId, Authentication authentication) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        User commentOwner = comment.getUser();
        if (permissionService.isThisUserOrAdmin(authentication.getName(), commentOwner)) {
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
        User commentOwner = comment.getUser();
        if (permissionService.isThisUserOrAdmin(authentication.getName(), commentOwner)) {
            if (comment.getAds().getId() != adId) {
                throw new AdsNotFoundException();
            }
            comment.setText(commentDto.getText());
            commentRepository.save(comment);
            adsRepository.save(comment.getAds());
            return commentMapper.commentToCommentDto(comment);
        }
        return commentDto;
    }
}
