package com.service.gameservice.user.resource;

import com.service.gameservice.domain.AuthFilter;
import com.service.gameservice.user.entity.User;
import com.service.gameservice.user.repository.UserRepositoryImpl;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

@Path("/user")
@Stateless
public class UserResource {

    @Inject
    private UserRepositoryImpl userRepository;

    @GET
    @Path("/id")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserById(@QueryParam("id") Long id){
        User user = userRepository.findUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("User with given id not exists").build();
        }
        return Response.ok(user).status(200).build();
    }

    @DELETE
    @Path("/deleteUser")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteUser(@QueryParam("id")Long id){
        userRepository.deleteUser(id);
        return Response.ok("Done").status(200).build();
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/name")
    public Response getUserByName(@QueryParam("userName") String name){
        User user = userRepository.findUserByName(name);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("User with given name not exists").build();
        }
        return Response.ok(user).status(200).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/email")
    public Response getUserByEmail(@QueryParam("userEmail")String email){
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("User with given email not exists").build();
        }
        return Response.ok(user).status(200).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/login")
    public Response loggedUser(@NotNull @QueryParam("userName")String name,
                               @NotNull @QueryParam("userPassword")String password){

        User user = userRepository.findUserByName(name);

        if(userRepository.checkUserCredentials(name,password)!=null && Objects.equals(userRepository.checkIfLogged(user.getId()), "logged")){
            return Response.ok("User was logged already").build();
        }

        AuthFilter authFilter = new AuthFilter();

        if(authFilter.authUser(name,password)) {
            return Response.ok("User was logged successfully").build();
        }else{
            return Response.ok("Wrong credentials").build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/ifLogged")
    public Response checkUserStatusLogging(@NotNull @QueryParam("id")Long id){
        User user = userRepository.findUserById(id);

        if(Objects.equals(userRepository.checkIfLogged(user.getId()), "logged")){
            return Response.ok("User is logged").build();
        }else{
            return Response.ok("User is not logged").build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/logout")
    public Response logOutUser(@NotNull @QueryParam("id")Long id){
        User user = userRepository.findUserById(id);

        if(Objects.equals(userRepository.checkIfLogged(user.getId()), "logged")){
            userRepository.logoutUser(user.getId());
            return Response.ok("User is unlogged").build();
        }else{
            return Response.ok("User is not logged already").build();
        }

    }

/*
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "")
    public Response checkIfUserIsAdmin(@QueryParam("id")Long id){
        User user = userRepository.checkIfUserIsAdmin(id);

        if(user == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User with given id not exists").build();
        }

        return Response.ok().status(200).build();
    }

 */
    @POST
    @Path("/add/new_user")
    @Produces({MediaType.APPLICATION_JSON})
    public Response createUser(@NotNull @QueryParam("userName")String name,
                               @NotNull @QueryParam("userPassword")String password,
                               @NotNull @QueryParam("userEmail")String email){
        userRepository.getEntityManager();
        if(name.isEmpty() || password.isEmpty()){
            return Response.status(Response.Status.FORBIDDEN).
                    entity("There is a forbidden input!").build();
        }
        if(userRepository.checkIfUserNameExist(name)){
            return Response.status(Response.Status.FORBIDDEN).
                    entity("User with that name exists!").build();
        }

        User user = new User();
            user.setUserName(name);
            user.setUserEmail(email);
        user.setUserPassword(userRepository.encodePassword(password));
        if(user.getUserPassword() == null){
                return Response.status(Response.Status.FORBIDDEN).
                        entity("There is a forbidden input!").build();
            }

            userRepository.addUser(user);
            return Response.ok(user).build();

    }




}
