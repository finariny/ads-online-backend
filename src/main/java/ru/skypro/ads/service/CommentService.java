package ru.skypro.ads.service;

import org.springframework.security.core.Authentication;
import ru.skypro.ads.dto.CommentDto;
import ru.skypro.ads.dto.CreateCommentDto;
import ru.skypro.ads.dto.ResponseWrapperCommentDto;

public interface CommentService {

    /**
     * Метод для получения всех комментарий к объявлению
     *
     * @param id принимает идентификатор объявления
     * @return возвращает комментарии
     */
    ResponseWrapperCommentDto getComments(int id);

    /**
     * Метод для добавления комментария в объявление
     *
     * @param id   принимает идентификатор объявления
     * @param text текст комментария
     * @return возвращает текст комментария
     */
    CommentDto addComment(int id, CreateCommentDto text);

    /**
     * Написать ошибку 403, указывает на комментарий, который нельзя удалить
     * Метод для удаления комментария
     *
     * @param adId      идентификатор объявления
     * @param commentId идентификатор комментария
     * @return возвращает статус удаленного элемента
     */
    boolean deleteComment(int adId, int commentId, Authentication authentication);

    /**
     * Написать ошибку 403, указывает на комментарий, который нельзя удалить
     * Метод для изменения или обновления комментария
     *
     * @param adId      идентификатор объявления
     * @param commentId идентификатор комментария
     * @return возвращает обновленный комментарий
     */
    CommentDto updateComment(Integer adId, Integer commentId, CommentDto commentDto, Authentication authentication);
}
