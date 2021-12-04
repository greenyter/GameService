package com.service.gameservice.comment_user.repository;

import com.service.gameservice.comment_user.entity.Comment;
import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class CommentRepositoryImpl implements CommentRepository{

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    private void getEntityManager() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Testing");
        em = entityManagerFactory.createEntityManager();
    }


    @Override
    @Transactional
    public void addComment(Comment comment) {
        getEntityManager();
        em.getTransaction().begin();
        em.persist(comment);
        em.getTransaction().commit();
    }

    @Override
    @Transactional
    public void modifyComment(Long id_user,Long id_game,String text) {
        getEntityManager();
        em.getTransaction().begin();
        String query = "UPDATE Comment SET commentText=:text WHERE idGame=:id_game AND idUser=:id_user";
        int executeUpdate= em.createQuery(query)
                .setParameter("text", text)
                .setParameter("id_game", id_game)
                .setParameter("id_user", id_user)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
