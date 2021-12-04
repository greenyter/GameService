package com.service.gameservice.gameratinguser.repository;

import com.service.gameservice.comment_user.entity.Comment;
import com.service.gameservice.gameratinguser.entity.GameRatingUser;

public interface GameRatingUserRepository {

    //POST
    void addRate(GameRatingUser rate);

    //update
    void modifyRate(Long id_user,Long id_game,int rate);
}
