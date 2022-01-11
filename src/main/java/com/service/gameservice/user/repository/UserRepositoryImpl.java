package com.service.gameservice.user.repository;

import com.service.gameservice.user.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import javax.persistence.*;


@ApplicationScoped
@Transactional
public class UserRepositoryImpl implements UserRepository {


    @PersistenceContext
    private EntityManager em;


    public UserRepositoryImpl(EntityManager em){
        this.em = em;
    }
    public UserRepositoryImpl(){}

    public void getEntityManager() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Testing");
        em = entityManagerFactory.createEntityManager();
    }


    @Override
    public User findUserById(Long id) {
        if(em == null){
            getEntityManager();
        }
        em.clear();
        return em.find(User.class, id);
    }

    @Override
    public User findUserByName(String name) {
        if(em == null){
            getEntityManager();
        }
        em.clear();
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class);
        q.setParameter("userName", name);
        return q.getSingleResult();
    }

    @Override
    public User findUserByEmail(String email) {
        if(em == null){
            getEntityManager();
        }
        em.clear();
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.userEmail = :userEmail", User.class);
        q.setParameter("userEmail", email);
        return q.getSingleResult();
    }

    @Override
    public User checkUserCredentials(String userName, String password) {
        if(em == null){
            getEntityManager();
        }
        em.clear();
        String encodePassword = encodePassword(password);
        try {

            Query credentials = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName AND u.userPassword = :encodePassword", User.class);
            credentials.setParameter("userName", userName);
            credentials.setParameter("encodePassword", encodePassword);
            return (User) credentials.getSingleResult();
        }catch (Exception e){
            e.fillInStackTrace();
        }

        return null;
    }
    /*
    @Override
    public User checkIfUserIsAdmin(Long id) {
        getEntityManager();        TypedQuery<User> q = em.createQuery("SELECT u.isAdmin FROM User u WHERE u.id = :id", User.class);

        q.setParameter("id",id);

        return q.getSingleResult();
    }

     */

    @Override
    @Transactional
    public void addUser(User user) {
        if(em == null){
            getEntityManager();
        }
        em.clear();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void changeUserEmail(Long id, String email) {

    }

    @Override
    public void promoteToAdmin(Long id) {

    }

    @Override
    public String encodePassword(String password) {
        int key = 18;
        char [] pssArray = password.toCharArray();
        for(int i=0;i<pssArray.length;i++){
            int ascii = pssArray[i];
            ascii*=key;
            pssArray[i]=(char)ascii;
        }
        return String.valueOf(pssArray);

    }

    @Override
    public boolean checkIfUserNameExist(String userName) {
        try {
            if(em == null){
                getEntityManager();
            }
            em.clear();
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class);
            q.setParameter("userName", userName);
            q.getSingleResult();
            return true;
        }catch (NoResultException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public void sendTokenForUserToDb(String token,Long id) {
        if(em == null){
            getEntityManager();
        }
        em.clear();
        em.getTransaction().begin();
        String query = "UPDATE User SET tokenUser=:token WHERE id=:id";
        Query executeUpdate= em.createQuery(query);
                executeUpdate.setParameter("token", token);
                executeUpdate.setParameter("id", id).executeUpdate();
        em.getTransaction().commit();

    }

    @Override
    @Transactional
    public void logoutUser(Long id) {
        if(em == null){
            getEntityManager();
        }
        em.clear();
        em.getTransaction().begin();
        String query = "UPDATE User SET tokenUser='notlogged' WHERE id=:id";
        int executeUpdate= em.createQuery(query).setParameter("id", id).executeUpdate();
        em.getTransaction().commit();


    }

    @Override
    public String checkIfLogged(Long id) {
        if(em == null){
            getEntityManager();
        }
        em.clear();
        return  findUserById(id).getTokenUser();
    }

    @Override
    public void deleteUser(Long id) {
        if(em == null){
            getEntityManager();
        }
        em.clear();
        em.getTransaction().begin();
        User user = em.find(User.class,id);
        em.remove(user);
        em.getTransaction().commit();
    }
}
