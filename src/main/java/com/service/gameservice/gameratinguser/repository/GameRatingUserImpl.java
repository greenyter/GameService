package com.service.gameservice.gameratinguser.repository;

import com.service.gameservice.gameratinguser.entity.GameRatingUser;
import jakarta.transaction.Transactional;

import javax.persistence.*;

public class GameRatingUserImpl implements GameRatingUserRepository{

    @PersistenceContext(unitName = "default")
    private EntityManager em;


    /**
     * Setting entity manager by persistence unit name provided by persistence.xml in
     * resources
     */
    private void getEntityManager() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Testing");
        em = entityManagerFactory.createEntityManager();
    }

    /**
     * Add new comment
     * @param rate object class's rate
     */
    @Override
    @Transactional
    public void addRate(GameRatingUser rate) {
        getEntityManager();
        em.getTransaction().begin();
        em.persist(rate);
        em.getTransaction().commit();
    }

    /**
     * Modify comment
     * @param id_user id of user which added comment
     * @param id_game id of game where comment was added
     * @param rate    modified rate
     */
    @Override
    public void modifyRate(Long id_user, Long id_game, int rate) {
        getEntityManager();
        em.getTransaction().begin();
        String query = "UPDATE GameRatingUser SET gameRatingUser=:rate WHERE idGame=:id_game AND idUser=:id_user";
        int executeUpdate= em.createQuery(query)
                .setParameter("rate", rate)
                .setParameter("id_game", id_game)
                .setParameter("id_user", id_user)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();

    }

    /**
     * Get rate added by provided user in particular game
     * @param id id of user which added comment
     * @return comment added that user
     */
    @Override
    public GameRatingUser getCommentByIdUser(Long id) {
        getEntityManager();
        TypedQuery<GameRatingUser> q = em.createQuery("SELECT c FROM GameRatingUser c WHERE c.idUser = :id", GameRatingUser.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
}
