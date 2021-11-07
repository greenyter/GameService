package com.service.gameservice.user.resource;

import com.service.gameservice.user.entity.User;
import com.service.gameservice.user.repository.UserRepositoryImpl;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import java.awt.*;

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
        User user = new User();
        user.setUserName(name);
        user.setUserPassword(password.getBytes());
        user.setUserEmail(email);
        userRepository.addUser(user);
        return Response.ok(user).build();
    }


}
