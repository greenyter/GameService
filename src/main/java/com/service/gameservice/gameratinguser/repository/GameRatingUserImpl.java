package com.service.gameservice.gameratinguser.repository;

import com.service.gameservice.comment_user.repository.CommentRepositoryImpl;
import com.service.gameservice.game.repository.GameRepositoryImpl;
import com.service.gameservice.gameratinguser.entity.GameRatingUser;
import com.service.gameservice.user.repository.UserRepositoryImpl;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class GameRatingUserImpl implements GameRatingUserRepository{

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    private void getEntityManager() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Testing");
        em = entityManagerFactory.createEntityManager();
    }

    @Override
    @Transactional
    public void addRate(GameRatingUser rate) {
        getEntityManager();
        em.getTransaction().begin();
        em.persist(rate);
        em.getTransaction().commit();
    }

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
}
