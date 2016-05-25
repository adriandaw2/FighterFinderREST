/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enma.proven.ffinder.resources;

import com.google.gson.Gson;
import enma.proven.ffinder.entities.AUser;
import enma.proven.ffinder.services.UserService;
import java.util.HashMap;
import java.util.List;
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
        Gson gson = new Gson();
        aUserService = new UserService(aNick, aPassword);
        AUser aU = aUserService.getUserFromDatabase();
        HashMap<String, Object> aMap = new HashMap();
        aMap.put("user", aU);
        String jsonString = gson.toJson(aMap);
        /*if(aU != null)
        {
            dataMsg=aU.toString();
        }*/
        return Response.ok().entity(jsonString).build();
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
     * @param aNewUbication
     * @param aIdObjective
     * @param currentNick
     * @return 
     */
    @Path("mod")
    @POST
    public Response modUserFromDatabase(@FormParam("userID") int aUserID, @FormParam("nick") String aNewNick, @FormParam("pass") String aNewPass, @FormParam("ubication") String aNewUbication,@FormParam("idObjective") int aIdObjective, @FormParam("currentNick") String currentNick)
    {
        int result = 0;
        aUserService = new UserService();
        AUser aU = new AUser(aUserID, aNewNick, aNewPass, aNewUbication, aIdObjective);
        result = aUserService.modifyUser(aU, currentNick);
        return Response.ok(result).build();
    }
    
    /**
     * searchUserByNickname
     * FUnction to get all the results from the search query
     * @param nickToSearch
     * @return Response
     */
    @Path("searchUserByNickname")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchUserByNickname(@FormParam("nickToSearch") String nickToSearch)
    {
        aUserService = new UserService();
        Gson gson = new Gson(); 
        List<AUser> aList = aUserService.searchUserByNickname(nickToSearch);
        HashMap<String, List<AUser>> aMap = new HashMap();
        aMap.put("resultUserByNick", aList);
        String jsonResult = gson.toJson(aMap);
        return Response.ok().entity(jsonResult).build();
    }
    
    /**
     * searchUserByGame
     * Function to get all the results from the search query
     * @param gameToSearch
     * @return Response
     */
    @Path("searchUserByGame")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchUserByGame(@FormParam("gameToSearch") int gameToSearch)
    {
        aUserService = new UserService();
        Gson gson = new Gson();
        List<AUser> aList = aUserService.searchUserByGame(gameToSearch);
        HashMap<String, List<AUser>> aMap = new HashMap();
        aMap.put("resultUserByGame", aList);
        String jsonResult = gson.toJson(aMap);
        return Response.ok().entity(jsonResult).build();
    }
    
    /**
     * searchUserBySkill
     * Function to get all the results from the search query
     * @param skillLevel
     * @return Response
     */
    @Path("searchUserBySkill")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchUserBySkill(@FormParam("skillLevel") int skillLevel)
    {
        aUserService = new UserService();
        Gson gson = new Gson();
        List<AUser> aList = aUserService.searchUSkillSame(skillLevel);
        HashMap<String, List<AUser>> aMap = new HashMap();
        aMap.put("resultUserBySkill", aList);
        String jsonResult = gson.toJson(aMap);
        return Response.ok().entity(jsonResult).build();
    }
    
    /**
     * rateUSkill
     * Function to rate a user skill
     * @param uWhoRate
     * @param uRated
     * @param skillRate
     * @return Response
     */
    @POST
    @Path("rateUSkill")
    public Response rateUSkill(@FormParam("uWhoRate")int uWhoRate, @FormParam("uRated") int uRated, @FormParam("skillRate") int skillRate)
    {
        int result = 0;
        aUserService = new UserService();
        result = aUserService.rateUSkill(uWhoRate, uRated, skillRate);
        return Response.ok(result).build();
    }
    
    /**
     * getAllUserFavs
     * Function to get all the user favs
     * @param uID
     * @return Response
     */
    @Path("getAllUserFavs")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUserFavs(@FormParam("userid")int uID)
    {
        aUserService = new UserService();
        Gson gson = new Gson();
        List<AUser> aList = aUserService.getAllUserFav(uID);
        HashMap<String, List<AUser>> aMap = new HashMap();
        aMap.put("resultUserFavs", aList);
        String jsonResult = gson.toJson(aMap);
        return Response.ok().entity(jsonResult).build();
    }
    
    /**
     * addUserToFav
     * Function to add a user to a user favorite list
     * @param uID
     * @param uToAddID
     * @return Response
     */
    @POST
    @Path("addUserToFav")
    public Response addUserToFav(@FormParam("userid")int uID, @FormParam("useridtoadd")int uToAddID)
    {
        int result = 0;
        aUserService = new UserService();
        result = aUserService.addUserToFav(uID, uToAddID);
        return Response.ok(result).build();
    }
    
    /**
     * deleteUserFromFav
     * Function to delete a user from a user favorite list
     * @param uID
     * @param uToDeleteID
     * @return Response
     */
    @POST
    @Path("deleteUserFromFav")
    public Response deleteUserFromFav(@FormParam("userid")int uID, @FormParam("useridtodelete")int uToDeleteID)
    {
        int result = 0;
        aUserService = new UserService();
        result = aUserService.deleteuserFromFav(uID, uToDeleteID);
        return Response.ok(result).build();
    }
    
    /**
     * sendRestorePassNickEmail
     * Function to restore the password and to know the current nick of the account. It uses the email
     * @param uEmail
     * @return Response
     */
    @POST
    @Path("restoreForgPass")
    public Response sendRestorePassNickEmail(@FormParam("aEmail") String uEmail)
    {
        aUserService = new UserService();
        int result = aUserService.sendEmailChangePassword(uEmail);
        return Response.ok(result).build();
    }
    
    /**
     * changeCurrentPassword
     * Function to change the current password
     * @param uID
     * @param newPass
     * @return Response
     */
    @POST
    @Path("changeCurrentPass")
    public Response changeCurrentPassword(@FormParam("uid") int uID, @FormParam("newPass") String newPass)
    {
        aUserService = new UserService();
        int result = aUserService.changeCurrentPassword(uID, newPass);
        
        return Response.ok(result).build();
    }
    
    /**
     * sendEmailToActivate
     * Function to send an email to activate account
     * @param aEmail
     * @return Respone
     */
    @POST
    @Path("sendEmailToActivate")
    public Response sendEmailToActivate(@FormParam("uemail") String aEmail)
    {
        int result = 0;
        aUserService = new UserService();
        result = aUserService.sendActivationEmail(aEmail);
        return Response.ok(result).build();
    }
    
    /**
     * sendEmailToDeactivate
     * Function to send an email to deactivate account
     * @param aEmail
     * @return Respone
     */
    @POST
    @Path("sendEmailToDeactivate")
    public Response sendEmailToDeactivate(@FormParam("uemail") String aEmail)
    {
        int result = 0;
        aUserService = new UserService();
        result = aUserService.sendDeactivationEmail(aEmail);
        return Response.ok(result).build();
    }
    
    
    /**
     * validateAccountUser
     * Function to activate the user account
     * @param uMail
     * @return Response
     */
    @Path("activateAcc/{umail}")
    @GET
    public Response validateAccountUser(@PathParam("umail") String uMail)
    {
        aUserService = new UserService();
        int result = 0;
        result = aUserService.activateUserAccount(uMail);
        String msgRes = "";
        if(result == -1)
        {
            msgRes = "Server is under mantenaince.";
        }
        else if(result == 0)
        {
            msgRes = "User already active";
        }else{
            msgRes = "You can now log into FighterFinder";
        }
        return Response.ok(msgRes).build();
    }
    
    /**
     * deactivateAccountUser
     * Function to activate the user account
     * @param uMail
     * @return Response
     */
    @Path("deactivateAcc/{umail}")
    @GET
    public Response deactivateAccountUser(@PathParam("umail") String uMail)
    {
        aUserService = new UserService();
        int result = 0;
        result = aUserService.deactivateUserAccount(uMail);
        String msgRes = "";
        if(result == -1)
        {
            msgRes = "Server is under mantenaince.";
        }
        else if(result == 0)
        {
            msgRes = "User already deactive";
        }else{
            msgRes = "Your account is now deactivated";
        }
        return Response.ok(msgRes).build();
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
