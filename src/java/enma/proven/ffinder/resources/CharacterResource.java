/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.resources;

import enma.proven.ffinder.entities.ACharacter;
import enma.proven.ffinder.services.CharacterService;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Adrian
 */
@Path("character")
public class CharacterResource {
    CharacterService aCharService;

    public CharacterResource() {
        aCharService = new CharacterService();
    }
    
    public CharacterResource(@Context ServletContext context) {
        aCharService = new CharacterService();
    }
    
    
    /**
     * getCharacterFromDatabase
     * Function to return all the character from one game
     * @param aGameID
     * @return Response
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharacterFromDatabase(@FormParam("gameid") int aGameID)
    {
        Collection<ACharacter> aCharCollection = aCharService.getAllCharacterFromGame(aGameID);
        GenericEntity<Collection<ACharacter>> result = new GenericEntity<Collection<ACharacter>>(aCharCollection){};
        return Response.ok().entity(result).build();       
    }
    
}
