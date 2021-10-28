package com.service.gameservice.resource;

import com.service.gameservice.entity.GameEntity;
import com.service.gameservice.service.GameService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;

@Path("/game")
public class GameResource {

    @Inject
    private GameService gameService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getGame(@PathParam("id") int id) {
        GameEntity game = gameService.findGame(id);

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
        List<GameEntity> games = gameService.getAllGames();

        if(games == null){
            return Response.status(Response.Status.NOT_FOUND).
                    entity("Sadly, there is no games here :(\nPlease, check later ^_^").build();
        }
        return Response.ok(games).build();
    }



}
