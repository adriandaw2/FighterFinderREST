/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enma.proven.ffinder.resources;

import enma.proven.ffinder.entities.AUser;
import enma.proven.ffinder.services.UserService;
import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alumne
 */
@Path("user")
public class UserResource {
    UserService aUserService;

    public UserResource(@Context ServletContext context) {
    }
    
    public UserResource() {
    }

    /**
     * userExist
     * Function to check if the user exist
     * @param aNick
     * @param aPassword
     * @return
     */
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response userExist(@FormParam("nick") String aNick, @FormParam("password") String aPassword)
    {
        String dataMsg ="No exist";
        aUserService = new UserService(aNick, aPassword);
        AUser aU = aUserService.getUserFromDatabase();
        /*if(aU != null)
        {
            dataMsg=aU.toString();
        }*/
        return Response.ok(aU).build();
    }
    
    /**
     * addUserToDatabase
     * Function to add a new user
     * @param aNewNick
     * @param aNewEmail
     * @param aNewPass
     * @return 
     */
    @Path("add")
    @POST
    public Response addUserToDatabase(@FormParam("aNick") String aNewNick, @FormParam("aEmail") String aNewEmail, @FormParam("aPass") String aNewPass)
    {
        int result = 0;
        aUserService = new UserService();
        AUser aU = new AUser(aNewNick, aNewEmail, aNewPass);
        result = aUserService.addUserToDatabase(aU);
        return Response.ok(result).build();
    }
    
    /**
     * modUserFromDatabase
     * Function to add a new user
     * @param aUserID
     * @param aNewNick
     * @param aNewPass
     * @param aIdObjective
     * @param currentNick
     * @return 
     */
    @Path("mod")
    @POST
    public Response modUserFromDatabase(@FormParam("userID") int aUserID, @FormParam("nick") String aNewNick, @FormParam("pass") String aNewPass, @FormParam("idObjective") int aIdObjective, @FormParam("currentNick") String currentNick)
    {
        int result = 0;
        aUserService = new UserService();
        AUser aU = new AUser(aUserID, aNewNick, aNewPass, aIdObjective);
        result = aUserService.modifyUser(aU, currentNick);
        return Response.ok(result).build();
    }
    
    
    
    /**
     * getInstancedAUser
     * Function to instanciate the AUser object to pass it to the resurdce to add it to the database
     * @param aNick
     * @param aPassword
     * @param aEmail
     * @return AUser
     */
    public AUser getInstancedAUser(String aNick, String aPassword, String aEmail)
    {
        AUser aU = new AUser(aNick, aPassword, aEmail);
        
        return aU;
    }
}
