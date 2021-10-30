package com.service.gameservice.game.repository;

import com.service.gameservice.game.entity.Game;

import javax.ejb.Stateless;
import java.util.List;

public interface GameRepository {

     Game findGameById(Long id);
     Game findGameByName(String name);
     List<Game> findAllGame();
}
