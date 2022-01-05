package com.service.gameservice.comment_user.repository;

import com.service.gameservice.comment_user.entity.CommentUser;

public interface CommentRepository {

    //POST
    void addComment(CommentUser commentUser);

    //update
    void modifyComment(Long id_user,Long id_game,String text);

    //GET
    CommentUser getCommentByIdUser(Long id);
}
