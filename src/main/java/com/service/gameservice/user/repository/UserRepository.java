package com.service.gameservice.user.repository;

import com.service.gameservice.user.entity.User;

public interface UserRepository {

    //GET
    User findUserById(Long id);
    User findUserByName(String name);
    User findUserByEmail(String email);
   //eee User checkIfUserIsAdmin(Long id);
    User checkUserCredentials(String userName, String password);

    //POST
    void addUser(User user);

    //PUT
    void changeUserEmail(Long id, String email);
    void promoteToAdmin(Long id);

    //utils
    String encodePassword(String password);
    boolean checkIfUserNameExist(String userName);
    void sendTokenForUserToDb(String token,Long id);
    void logoutUser(Long id);
    String checkIfLogged(Long id);
}
