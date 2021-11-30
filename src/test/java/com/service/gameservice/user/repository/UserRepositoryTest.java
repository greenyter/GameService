package com.service.gameservice.user.repository;


import com.service.gameservice.user.entity.User;
import jakarta.inject.Inject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserRepositoryTest {


    @PersistenceContext
    private EntityManager em;

    @Inject
    UserRepositoryImpl userRepository;

    public void getEntityManager() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("tests");
        em = entityManagerFactory.createEntityManager();
    }


    private void deleteCreatedRecord(String name){
        User findUser = userRepository.findUserByName(name);

        userRepository.deleteUser(findUser.getId());
    }

    @Test
    public void testCreateNewUser_ExpectSuccess(){
        getEntityManager();
        userRepository = new UserRepositoryImpl(em);
        User user = new User();
        user.setUserName("aaa");
        user.setUserPassword("12345678");
        user.setUserEmail("email");

        userRepository.addUser(user);

        userRepository.findUserByName("aaa");

        assertThat(user.getUserPassword(),equalTo("12345678"));
        assertThat(user.getUserName(),equalTo("aaa"));
        assertThat(user.getUserEmail(),equalTo("email"));

        deleteCreatedRecord("aaa");

    }

    @Test
    public void testFindById_ExpectSuccess(){
        getEntityManager();
        userRepository = new UserRepositoryImpl(em);
        User user = new User();
        user.setUserName("aaa");
        user.setUserPassword("12345678");
        user.setUserEmail("email");

        userRepository.addUser(user);
        Long id = user.getId();
        Assertions.assertNotNull(userRepository.findUserById(id));

        deleteCreatedRecord("aaa");
    }

    @Test
    public void testFindByName_ExpectSuccess(){
        getEntityManager();
        userRepository = new UserRepositoryImpl(em);
        User user = new User();
        user.setUserName("aaa");
        user.setUserPassword("12345678");
        user.setUserEmail("email");

        userRepository.addUser(user);

        Assertions.assertNotNull(userRepository.findUserByName("aaa"));

        deleteCreatedRecord("aaa");
    }

    @Test
    public void testCheckCredentials_ExpectSuccess(){
        getEntityManager();
        userRepository = new UserRepositoryImpl(em);
        User user = new User();
        user.setUserName("aaa");
        user.setUserPassword(userRepository.encodePassword("12345678"));
        user.setUserEmail("email");

        userRepository.addUser(user);
        Assertions.assertNotNull(userRepository.checkUserCredentials("aaa","12345678"));

        deleteCreatedRecord("aaa");
    }

    @Test
    public void testCheckIfUserExists_ExpectSuccess(){
        getEntityManager();
        userRepository = new UserRepositoryImpl(em);
        User user = new User();
        user.setUserName("aaa");
        user.setUserPassword(userRepository.encodePassword("12345678"));
        user.setUserEmail("email");

        userRepository.addUser(user);
        Assertions.assertTrue(userRepository.checkIfUserNameExist("aaa"));

        deleteCreatedRecord("aaa");
    }

    @Test
    public void testSendTokenForUserToDb_ExpectSuccess(){
        getEntityManager();
        userRepository = new UserRepositoryImpl(em);
        User user = new User();
        user.setUserName("aaa");
        user.setUserPassword(userRepository.encodePassword("12345678"));
        user.setUserEmail("email");

        userRepository.addUser(user);
        userRepository.sendTokenForUserToDb("logged",user.getId());
        assertThat(user.getTokenUser(),equalTo("logged"));

        deleteCreatedRecord("aaa");
    }


    @Test
    public void testCheckIfLogged_ExpectSuccess(){
        getEntityManager();
        userRepository = new UserRepositoryImpl(em);
        User user = new User();
        user.setUserName("aaa");
        user.setUserPassword(userRepository.encodePassword("12345678"));
        user.setUserEmail("email");
        user.setTokenUser("lol");
        userRepository.addUser(user);

        String result = userRepository.checkIfLogged(user.getId());
        assertThat(result,equalTo("lol"));

        deleteCreatedRecord("aaa");
    }
}
