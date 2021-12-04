package com.service.gameservice.comment_user.repository;

import com.service.gameservice.comment_user.entity.Comment;

public interface CommentRepository {

    //POST
    void addComment(Comment comment);

    //update
    void modifyComment(Long id_user,Long id_game,String text);
}
