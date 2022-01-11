package com.service.gameservice.game.resource;

import com.service.gameservice.game.entity.Game;
import com.service.gameservice.game.repository.GameRepositoryImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.ejb.Stateless;
import java.util.List;

@Path("/game")
@Stateless
public class GameResource {

    @Inject
    private GameRepositoryImpl gameRepository;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/id/{id}")
    public Response getGameById(@PathParam("id") Long id) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("Game with given id not exists").build();
        }
        return Response.ok(game).build();
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/name/{gameName}")
    public Response getGameByName(@PathParam("gameName") String name){
        Game game = gameRepository.findGameByName(name);

        if (game == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("Game with given id not exists").build();
        }
        return Response.ok(game).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllGame(){
        List<Game> games = gameRepository.findAllGame();

        if(games == null){
            return Response.status(Response.Status.NOT_FOUND).
                    entity("Sadly, there is no games here :(\nPlease, check later ^_^").build();
        }
        return Response.ok(games).build();
    }



}
