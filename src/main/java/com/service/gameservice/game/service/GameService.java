package com.service.gameservice.game.service;

import com.service.gameservice.game.entity.Game;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
@Transactional
public class GameService {

    @PersistenceContext
    private EntityManager entityManager;

    private void getEntityManager(){
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Testing");
        entityManager = entityManagerFactory.createEntityManager();
    }


    public Game findGame(int id){
        getEntityManager();
        return entityManager.find(Game.class,id);
    }

    public List<Game> getAllGames(){
        getEntityManager();
        return entityManager.createQuery("SELECT g FROM Game g", Game.class).getResultList();
    }
}
