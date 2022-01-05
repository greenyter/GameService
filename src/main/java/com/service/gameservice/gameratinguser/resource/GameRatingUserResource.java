package com.service.gameservice.gameratinguser.resource;

import com.service.gameservice.comment_user.entity.CommentUser;
import com.service.gameservice.game.repository.GameRepositoryImpl;
import com.service.gameservice.gameratinguser.entity.GameRatingUser;
import com.service.gameservice.gameratinguser.repository.GameRatingUserImpl;
import com.service.gameservice.user.entity.User;
import com.service.gameservice.user.repository.UserRepositoryImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.ejb.Stateless;

@Path("/gamerate")
@Stateless
public class GameRatingUserResource {
    @Inject
    private GameRatingUserImpl gameRatingUser;

    @Inject
    private UserRepositoryImpl userRepository;

    @Inject
    private GameRepositoryImpl gameRepository;

    @POST
    @Path("/add_rate/{idGame}/{idUser}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response addRate(@PathParam("idGame") Long idGame,
                               @PathParam("idUser") Long idUser,
                               @QueryParam("rate")int rate)    {

        if(rate<0 || rate>5){
            return Response.status(401).entity("Sorry, but this rate is wrong : /").build();
        }

        if(userRepository.findUserById(idUser)==null){
            return Response.status(404).entity("User does not exist").build();
        }
        if (gameRepository.findGameById(idGame)==null){
            return Response.status(404).entity("Game does not exist").build();
        }
        GameRatingUser rateObject = new GameRatingUser();
        rateObject.setGameRatingUser(rate);
        rateObject.setIdGame(idGame);
        rateObject.setIdUser(idUser);
        gameRatingUser.addRate(rateObject);

        return Response.status(200).entity(gameRatingUser).build();
    }

    @PUT
    @Path("/edit_rate/{idGame}/{idUser}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response editRate(@PathParam("idGame") Long idGame,
                                @PathParam("idUser") Long idUser,
                                @QueryParam("rate")int rate){

        if(rate<0 || rate>5){
            return Response.status(401).entity("Sorry, but this rate is wrong : /").build();
        }

        if(userRepository.findUserById(idUser)==null){
            return Response.status(404).entity("User does not exist").build();
        }
        if (gameRepository.findGameById(idGame)==null){
            return Response.status(404).entity("Game does not exist").build();
        }

        gameRatingUser.modifyRate(idUser,idGame,rate);

        return Response.status(200).entity("UPDATED").build();
    }

    @GET
    @Path("/id")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCommentByIdUser(@QueryParam("idUser") Long id){
        User user = userRepository.findUserById(id);
        GameRatingUser gameRatingUser = this.gameRatingUser.getCommentByIdUser(id);
        if (user == null && gameRatingUser==null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("User with given id not exists").build();
        }
        return Response.ok(gameRatingUser).status(200).build();
    }
}
