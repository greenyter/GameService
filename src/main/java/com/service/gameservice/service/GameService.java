package com.service.gameservice.service;

import com.service.gameservice.entity.GameEntity;
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


    public GameEntity findGame(int id){
        getEntityManager();
        return entityManager.find(GameEntity.class,id);
    }

    public List<GameEntity> getAllGames(){
        getEntityManager();
        return entityManager.createQuery("SELECT g FROM GameEntity g",GameEntity.class).getResultList();
    }
}
