package com.service.gameservice.user.repository;

import com.service.gameservice.user.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserRepositoryTest {

    @Inject
    UserRepository userRepository;


    private void deleteCreatedRecord(String name){
        userRepository = new UserRepositoryImpl();
        User findUser = userRepository.findUserByName(name);

        userRepository.deleteUser(findUser.getId());
    }

    @Test
    public void testCreateNewUser_ExpectSuccess(){
        userRepository = new UserRepositoryImpl();
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
        userRepository = new UserRepositoryImpl();
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
        userRepository = new UserRepositoryImpl();
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
        userRepository = new UserRepositoryImpl();
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
        userRepository = new UserRepositoryImpl();
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
        userRepository = new UserRepositoryImpl();
        User user = new User();
        user.setUserName("aaa");
        user.setUserPassword(userRepository.encodePassword("12345678"));
        user.setUserEmail("email");

        userRepository.addUser(user);

        userRepository.sendTokenForUserToDb("logged",user.getId());
        String result = userRepository.checkIfLogged(user.getId());
        assertThat(result,equalTo("logged"));

        deleteCreatedRecord("aaa");
    }


    @Test
    public void testCheckIfLogged_ExpectSuccess(){
        userRepository = new UserRepositoryImpl();
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
