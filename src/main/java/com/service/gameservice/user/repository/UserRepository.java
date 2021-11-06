package com.service.gameservice.user.repository;

import com.service.gameservice.user.entity.User;

public interface UserRepository {

    //GET
    User findUserById(Long id);
    User findUserByName(String name);
    User findUserByEmail(String email);
   // User checkIfUserIsAdmin(Long id);


    //POST
    User addUser(User user);

    //PUT
    User changeUserEmail(Long id, String email);
    User promoteToAdmin(Long id);
}
