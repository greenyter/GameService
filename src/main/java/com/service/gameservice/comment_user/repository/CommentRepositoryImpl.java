package com.service.gameservice.comment_user.repository;

import com.service.gameservice.comment_user.entity.CommentUser;
import com.service.gameservice.game.entity.Game;
import com.service.gameservice.user.entity.User;
import jakarta.transaction.Transactional;

import javax.persistence.*;

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
    public void addComment(CommentUser commentUser) {
        getEntityManager();
        em.getTransaction().begin();
        em.persist(commentUser);
        em.getTransaction().commit();
    }

    @Override
    @Transactional
    public void modifyComment(Long id_user,Long id_game,String text) {
        getEntityManager();
        em.getTransaction().begin();
        String query = "UPDATE CommentUser SET commentText=:text WHERE idGame=:id_game AND idUser=:id_user";
        int executeUpdate= em.createQuery(query)
                .setParameter("text", text)
                .setParameter("id_game", id_game)
                .setParameter("id_user", id_user)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public CommentUser getCommentByIdUser(Long id) {
        getEntityManager();
        TypedQuery<CommentUser> q = em.createQuery("SELECT c FROM CommentUser c WHERE c.idUser = :id", CommentUser.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
}
