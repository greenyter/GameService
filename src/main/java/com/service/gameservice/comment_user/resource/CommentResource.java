package com.service.gameservice.comment_user.resource;

import com.service.gameservice.comment_user.entity.CommentUser;
import com.service.gameservice.comment_user.repository.CommentRepositoryImpl;
import com.service.gameservice.game.repository.GameRepositoryImpl;
import com.service.gameservice.user.entity.User;
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
    @Path("/add_comment")
    @Produces({MediaType.APPLICATION_JSON})
    public Response addComment(@QueryParam("idGame") Long idGame,
                                @QueryParam("idUser") Long idUser,
                                @QueryParam("text")String comment)    {
        if(userRepository.findUserById(idUser)==null){
           return Response.status(404).entity("User does not exist").build();
        }
        if (gameRepository.findGameById(idGame)==null){
            return Response.status(404).entity("Game does not exist").build();
        }
        CommentUser objectCommentUser = new CommentUser();
        objectCommentUser.setCommentText(comment);
        objectCommentUser.setIdGame(idGame);
        objectCommentUser.setIdUser(idUser);
        commentRepository.addComment(objectCommentUser);

        return Response.status(200).entity(objectCommentUser).build();
    }

    @PUT
    @Path("/edit_comment")
    @Produces({MediaType.APPLICATION_JSON})
    public Response editComment(@QueryParam("idGame") Long idGame,
                                @QueryParam("idUser") Long idUser,
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

    @GET
    @Path("/id")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCommentByIdUser(@QueryParam("idUser") Long id){
        User user = userRepository.findUserById(id);
        CommentUser commentUser = commentRepository.getCommentByIdUser(id);
        if (user == null && commentUser==null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("User with given id not exists").build();
        }
        return Response.ok(commentUser).status(200).build();
    }
}
