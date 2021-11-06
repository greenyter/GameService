package com.service.gameservice.user.repository;

import com.service.gameservice.user.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import javax.persistence.*;


@ApplicationScoped
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    private void getEntityManager() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Testing");
        em = entityManagerFactory.createEntityManager();
    }


    @Override
    public User findUserById(Long id) {
        getEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public User findUserByName(String name) {
        getEntityManager();
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class);
        q.setParameter("userName", name);
        return q.getSingleResult();
    }

    @Override
    public User findUserByEmail(String email) {
        getEntityManager();
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.userEmail = :userEmail", User.class);
        q.setParameter("userEmail", email);
        return q.getSingleResult();
    }
    /*
    @Override
    public User checkIfUserIsAdmin(Long id) {
        getEntityManager();
        TypedQuery<User> q = em.createQuery("SELECT u.isAdmin FROM User u WHERE u.id = :id", User.class);

        q.setParameter("id",id);

        return q.getSingleResult();
    }

     */

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User changeUserEmail(Long id, String email) {
        return null;
    }

    @Override
    public User promoteToAdmin(Long id) {
        return null;
    }
}
