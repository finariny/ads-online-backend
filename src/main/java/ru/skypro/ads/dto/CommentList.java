package ru.skypro.ads.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentList {
   private Integer count;
   List<Comment> results;
}
