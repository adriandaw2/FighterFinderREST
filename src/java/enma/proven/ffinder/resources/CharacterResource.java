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
     * @param aUserID
     * @return Response
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharacterFromDatabase(@FormParam("gameID") int aGameID, @FormParam("userID") int aUserID)
    {
        aCharService = new CharacterService();
        Collection<ACharacter> aCharCollection = aCharService.getAllCharacterFromGame(aGameID, aUserID);
        GenericEntity<Collection<ACharacter>> result = new GenericEntity<Collection<ACharacter>>(aCharCollection){};
        return Response.ok().entity(result).build();       
    }
    
    /**
     * addCharacterToUser
     * Function to add a cahracter to a user.
     * @param uID
     * @param cID
     * @return Response
     */
    @POST
    @Path("addCharToUser")
    public Response addCharacterToUser(@FormParam("userID") int uID, @FormParam("charID") int cID)
    {
        aCharService = new CharacterService();
        int result = aCharService.addCharacerToUser(uID, cID);

        return Response.ok(result).build();
    }
    
    
    /**
     * deletCharacterFromUser
     * Function to delete a character from a User
     * @param uID
     * @param cID
     * @return Response
     */
    @POST
    @Path("deleteCharUser")
    public Response deletCharacterFromUser(@FormParam("userid") int uID, @FormParam("characterid") int cID)
    {
        aCharService = new CharacterService();
        int result = aCharService.deleteCHaracterFromUser(uID, cID);
        return Response.ok(result).build();
    }
    
}
