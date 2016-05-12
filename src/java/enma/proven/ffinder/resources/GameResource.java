/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.resources;

import com.google.gson.Gson;
import enma.proven.ffinder.entities.AGame;
import enma.proven.ffinder.services.GameService;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alumne
 */
@Path("game")
public class GameResource {
    GameService aGameService;

    public GameResource() {
        aGameService = new GameService();
    }

    public GameResource(@Context ServletContext context) {
        aGameService = new GameService();
    }
    
    /**
     * getAllGamesFromDatabase
     * Function to get all the games in the database
     * @return Response
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGamesFromDatabase()
    {
        aGameService = new GameService();
        //Collection<AGame> allGamesList = aGameService.getAllGamesFromDatabase();
        //GenericEntity<Collection<AGame>> result = new GenericEntity<Collection<AGame>>(allGamesList){};
        Gson gson = new Gson();
        List<AGame> aList = aGameService.getAllGamesFromDatabase();
        String jsonResult = gson.toJson(aList);
        return Response.ok().entity(jsonResult).build();
    }
    
    /**
     * getGamesUserDontPlayFromDatabase
     * Function to get all the games in the database
     * @param uID
     * @return Response
     */
    @POST
    @Path("getGameNoPlayUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGamesUserDontPlayFromDatabase(@FormParam("userID") int uID)
    {
        aGameService = new GameService();
        //Collection<AGame> allGamesList = aGameService.getGamesUserDontPlayFromDatabase(uID);
        //GenericEntity<Collection<AGame>> result = new GenericEntity<Collection<AGame>>(allGamesList){};
        Gson gson = new Gson();
        List<AGame> aList = aGameService.getGamesUserDontPlayFromDatabase(uID);
        String jsonResult = gson.toJson(aList);
        return Response.ok().entity(jsonResult).build();
    }
    
    /**
     * addGameToUser
     * Function to add a game to one use
     * @param uID
     * @param gID
     * @return Response
     */
    @Path("addGame")
    @POST
    public Response addGameToUser(@FormParam("userID") int uID, @FormParam("gameID") int gID)
    {
        aGameService = new GameService();
        int result = aGameService.addGameToUser(uID, gID);
        return Response.ok(result).build();
    }
    
    /**
     * deletGameFromUser
     * Function to delete a game from a User
     * @param uID
     * @param gID
     * @return Response
     */
    @POST
    @Path("deleteGameUser")
    public Response deletGameFromUser(@FormParam("userID") int uID, @FormParam("gameID") int gID)
    {
        aGameService = new GameService();
        int result = aGameService.deleteGameFromUser(uID, gID);
        return Response.ok(result).build();
    }
}
