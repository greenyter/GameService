package com.service.gameservice.boundary;

import com.service.gameservice.entity.GameEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GameBoundary {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;



    public GameEntity findGame(Long id){
        return entityManager.find(GameEntity.class,id);
    }
}
