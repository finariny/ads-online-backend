package ru.skypro.ads.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
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
import ru.skypro.ads.dto.CommentCreate;
import ru.skypro.ads.dto.CommentList;
import ru.skypro.ads.service.CommentListService;


@Slf4j
@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentListController {

    private final CommentListService commentListService;

    @Operation(summary = "Получить комментарии объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401")
    })
    @Schema()
    @GetMapping(value = "/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentList> getComment(@PathVariable int id) {
        CommentList comment = commentListService.getComments(id);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(comment);
    }

    @Operation(summary = "Добавить комментарий к объявлению")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401")
    })
    @PostMapping(value = "/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> addComment(@PathVariable int id, @RequestBody CommentCreate text) {
        Comment addComment = commentListService.addComment(id, text);
        if (addComment != null) {
            return ResponseEntity.ok(addComment);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Написать ошибку 403, указывает на комментарий, который нельзя удалить
     * предполагаю что это комментарий админа
     *
     * @param adId
     * @param commentId
     * @return
     */
    @Operation(summary = "Удалить комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")
    })
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        if (commentListService.deleteComment(adId, commentId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Написать ошибку 403, указывает на комментарий, который нельзя удалить
     * предполагаю что это комментарий админа
     *
     * @param adId
     * @param commentId
     * @return
     */
    @Operation(summary = "Обновить комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")
    })
    @PatchMapping(value = "/{adId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> patchComment(@PathVariable int adId, @PathVariable int commentId, @RequestBody Comment comment) {
        Comment comment1 = commentListService.patchComment(adId, commentId, comment);
        if (comment1 != null) {
            return ResponseEntity.ok(comment1);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

