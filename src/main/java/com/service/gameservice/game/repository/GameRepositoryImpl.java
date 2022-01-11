package com.service.gameservice.game.repository;

import com.service.gameservice.game.entity.Game;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import javax.persistence.*;
import java.util.List;

@ApplicationScoped
@Transactional
public class GameRepositoryImpl implements GameRepository {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    /**
     * Setting entity manager by persistence unit name provided by persistence.xml in
     * resources
     */
    private void getEntityManager(){
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Testing");
        em = entityManagerFactory.createEntityManager();

    }

    /**
     * Get game by given id if exists.
     * @param id id of game
     * @return game with given id or null if not exists
     */
    @Override
    public Game findGameById(Long id){
        getEntityManager();
        return em.find(Game.class,id);
    }
    /**
     * Get game by given name if exists.
     * @param name id of game
     * @return game with given name or null if not exists
     */
    @Override
    public Game findGameByName(String name) {
        getEntityManager();
        TypedQuery<Game> q = em.createQuery("SELECT b FROM Game b WHERE b.gameName = :gameName", Game.class);
        q.setParameter("gameName", name);
        return q.getSingleResult();
    }

    /**
     * Return all existed games in db.
     * @return list of games
     */
    @Override
    public List<Game> findAllGame() {
        getEntityManager();
        return em.createQuery("SELECT g FROM Game g", Game.class).getResultList();
    }
}
