package com.service.gameservice.domain;

import com.service.gameservice.user.entity.User;
import com.service.gameservice.user.repository.UserRepositoryImpl;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AuthFilter {


    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();

    public void authUser(String userName, String password){



        if(getUserFromDB(userName,password)) {

            byte[] randomBytes = new byte[32];
            secureRandom.nextBytes(randomBytes);
            base64Encoder.encodeToString(randomBytes);
            String token = base64Encoder.encodeToString(randomBytes);
            User getUser = userRepository.findUserByName(userName);
            userRepository.sendTokenForUserToDb(token,getUser.getId());
        }
    }


    private boolean getUserFromDB(String userName,String password){

        return userRepository.checkUserCredentials(userName, password) != null;
    }

}
