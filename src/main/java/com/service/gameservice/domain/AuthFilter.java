package com.service.gameservice.domain;

import com.service.gameservice.user.entity.User;
import com.service.gameservice.user.repository.UserRepositoryImpl;

import java.security.SecureRandom;
import java.util.Base64;

public class AuthFilter {


    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();

    /**
     * Simply method to validate credentials given by user
     * @param userName userName of user
     * @param password password of user
     * @return true if credentials are valid, else return false
     */
    public boolean authUser(String userName, String password){



        if(getUserFromDB(userName,password)) {
            User getUser = userRepository.findUserByName(userName);


            userRepository.sendTokenForUserToDb("logged",getUser.getId());
            return true;
        }
        return false;
    }

    /**
     * Checking if user even exists.
     * @param userName userName of user
     * @param password password of user
     * @return true if user exists, else false
     */
    private boolean getUserFromDB(String userName,String password){

        return userRepository.checkUserCredentials(userName, password) != null;
    }

}
