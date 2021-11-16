package com.service.gameservice.user.repository;

import com.service.gameservice.user.entity.User;
import com.service.gameservice.user.resource.util.EntityManagerProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.management.Query;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UserRepositoryIT {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.withUnit("integration-test");



    @Test
    public void testCreateNewUser_ExpectSuccess(){
        //given
        User user = new User();
        user.setUserName("grrffgdgfdgfdeen");
        user.setUserPassword("123456789");
        user.setUserEmail("afgdfdgfgdaa");

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.addUser(user);


    }
}
