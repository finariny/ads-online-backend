package ru.skypro.ads.service;

import ru.skypro.ads.dto.Comment;
import ru.skypro.ads.dto.CommentCreate;
import ru.skypro.ads.dto.CommentList;

public interface CommentListService {

    /**
     * Метод для получения всех комментарий к объявлению
     * @param id принимает идентификатор объявления
     * @return возвращает комментарии
     */
    CommentList getComments(int id);

    /**
     * Метод для добавления комментария в объявление
     * @param id   принимает идентификатор объявления
     * @param text текст комментария
     * @return возвращает текст комментария
     */
    Comment addComment(int id, CommentCreate text);

    /**
     * Написать ошибку 403, указывает на комментарий, который нельзя удалить
     * предполагаю что это комментарий админа
     * Метод для удаления комментария
     * @param adId      идентификатор объявления
     * @param commentId идентификатор комментария
     * @return возвращает статус удаленного элемента
     */
    boolean deleteComment(int adId, int commentId);

    /**
     * Написать ошибку 403, указывает на комментарий, который нельзя удалить
     * предполагаю что это комментарий админа
     * Метод для изменения или обновления комментария
     * @param adId      идентификатор объявления
     * @param commentId идентификатор комментария
     * @return возвращает обновленный комментарий
     */
    Comment updateComment(Integer adId, Integer commentId, Comment comment);

}
