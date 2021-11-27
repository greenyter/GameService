package com.service.gameservice.comment_user;

public interface CommentRepository {

    //POST
    void addComment(Comment comment);

    //update
    void modifyComment(Long id_user,Long id_game,String text);
}
