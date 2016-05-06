/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.resources;

import enma.proven.ffinder.entities.AGame;
import enma.proven.ffinder.services.GameService;
import java.util.Collection;
import javax.servlet.ServletContext;
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
        Collection<AGame> allGamesList = aGameService.getAllGamesFromDatabase();
        GenericEntity<Collection<AGame>> result = new GenericEntity<Collection<AGame>>(allGamesList){};
        return Response.ok().entity(result).build();
    }
    
    
}
