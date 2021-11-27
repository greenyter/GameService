package com.service.gameservice.comment_user;

import com.service.gameservice.game.repository.GameRepository;
import com.service.gameservice.game.repository.GameRepositoryImpl;
import com.service.gameservice.user.repository.UserRepositoryImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.ejb.Stateless;

@Path("/comment")
@Stateless
public class CommentResource{

   @Inject
   private CommentRepositoryImpl commentRepository;

   @Inject
   private UserRepositoryImpl userRepository;

   @Inject
   private GameRepositoryImpl gameRepository;

    @POST
    @Path("/add_comment/{idGame}/{idUser}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response addComment(@PathParam("idGame") Long idGame,
                                @PathParam("idUser") Long idUser,
                                @QueryParam("text")String comment)    {
        if(userRepository.findUserById(idUser)==null){
           return Response.status(404).entity("User does not exist").build();
        }
        if (gameRepository.findGameById(idGame)==null){
            return Response.status(404).entity("Game does not exist").build();
        }
        Comment objectComment = new Comment();
        objectComment.setCommentText(comment);
        objectComment.setIdGame(idGame);
        objectComment.setIdUser(idUser);
        commentRepository.addComment(objectComment);

        return Response.status(200).entity(objectComment).build();
    }

    @PUT
    @Path("/edit_comment/{idGame}/{idUser}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response editComment(@PathParam("idGame") Long idGame,
                                @PathParam("idUser") Long idUser,
                                @QueryParam("text")String comment){


        if(userRepository.findUserById(idUser)==null){
            return Response.status(404).entity("User does not exist").build();
        }
        if (gameRepository.findGameById(idGame)==null){
            return Response.status(404).entity("Game does not exist").build();
        }

        commentRepository.modifyComment(idUser,idGame,comment);

        return Response.status(200).entity("UPDATED").build();
    }
}
