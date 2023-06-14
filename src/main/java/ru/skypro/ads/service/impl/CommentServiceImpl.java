package ru.skypro.ads.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.ads.dto.CommentDto;
import ru.skypro.ads.dto.CreateCommentDto;
import ru.skypro.ads.dto.ResponseWrapperCommentDto;
import ru.skypro.ads.entity.Ads;
import ru.skypro.ads.entity.Comment;
import ru.skypro.ads.mapper.CommentMapper;
import ru.skypro.ads.repository.AdsRepository;
import ru.skypro.ads.repository.CommentRepository;
import ru.skypro.ads.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private AdsRepository adsRepository;
    private CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, AdsRepository adsRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public ResponseWrapperCommentDto getComments(int id) {
        if (adsRepository.findById(id).isPresent()) {
            Ads ads = adsRepository.findById(id).get();
            return commentMapper.listCommentToCommentDto(ads.getAdsCommentList().size(), ads.getAdsCommentList());
        }
        return null;
    }

    @Override
    public CommentDto addComment(int id, CreateCommentDto text) {
        Optional<Ads> ads = adsRepository.findById(id);
        if (ads.isPresent()) {
            Comment comment = new Comment();
            commentMapper.updateCommentFromCommentDto(text, comment);
            List<Comment> adsCommentList = ads.get().getAdsCommentList();
            adsCommentList.add(comment);
            Comment save = commentRepository.save(comment);
            return commentMapper.commentToCommentDto(save);
        }
        return null;
    }

    @Override
    public boolean deleteComment(int adId, int commentId) {
        Optional<Ads> ads = adsRepository.findById(adId);
        if (ads.isPresent()) {
            List<Comment> adsCommentList = ads.get().getAdsCommentList();
            Comment comment = adsCommentList.get(commentId);
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CommentDto commentDto) {
        Optional<Ads> ads = adsRepository.findById(adId);
        if (ads.isPresent()) {
            if (commentRepository.findById(commentId).isPresent()) {
                Comment comment = commentRepository.findById(commentId).get();
                commentMapper.updateCommentFromCommentDto(commentDto, comment);
                Comment save = commentRepository.save(comment);
                return commentMapper.commentToCommentDto(save);
            }
        }
        return null;
    }
}


