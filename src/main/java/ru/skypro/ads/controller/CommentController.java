package ru.skypro.ads.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.ads.dto.Comment;
import ru.skypro.ads.dto.CreateComment;
import ru.skypro.ads.dto.ResponseWrapperComment;
import ru.skypro.ads.service.CommentService;

@Slf4j
@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentController {


    private final CommentService commentService;


    @PostMapping(value = "/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Добавить комментарий к объявлению")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    public ResponseEntity<Comment> addComment(@PathVariable int id, @RequestBody CreateComment text) {
        Comment addComment = commentService.addComment(id, text);
        if (addComment != null) {
            return ResponseEntity.ok(addComment);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @GetMapping(value = "/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получить комментарии объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<ResponseWrapperComment> getComment(@PathVariable int id) {
        ResponseWrapperComment comment = commentService.getComments(id);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(comment);
    }


    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удалить комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Void> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        if (adId <= 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (commentService.deleteComment(adId, commentId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PatchMapping(value = "/{adId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Обновить комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Comment> updateComment(@PathVariable int adId, @PathVariable int commentId, @RequestBody Comment comment) {
        if (adId <= 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Comment comment1 = commentService.updateComment(adId, commentId, comment);
        if (comment1 != null) {
            return ResponseEntity.ok(comment1);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
