package com.service.gameservice.game.repository;

import com.service.gameservice.game.entity.Game;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
@Transactional
public class GameRepositoryImpl implements GameRepository {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    private void getEntityManager(){
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Testing");
        em = entityManagerFactory.createEntityManager();

    }


    @Override
    public Game findGameById(Long id){
        getEntityManager();
        return em.find(Game.class,id);
    }

    @Override
    public Game findGameByName(String name) {
        getEntityManager();
        TypedQuery<Game> q = em.createQuery("SELECT b FROM Game b WHERE b.gameName = :gameName", Game.class);
        q.setParameter("gameName", name);
        return q.getSingleResult();
    }

    @Override
    public List<Game> findAllGame() {
        getEntityManager();
        return em.createQuery("SELECT g FROM Game g", Game.class).getResultList();
    }
}
