/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enma.proven.ffinder.resources;

import enma.proven.ffinder.entities.AUser;
import enma.proven.ffinder.services.LoginService;
import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alumne
 */
@Path("login")
//@Produces({"application/xml", "application/json"})
public class LoginResource {
    LoginService aLoginService;

    public LoginResource(@Context ServletContext context) {
    }
    
    public LoginResource() {
    }
    
    @POST
    public Response userExist(@FormParam("nick") String aNick, @FormParam("password") String aPassword)
    {
        String dataMsg ="No exist";
        aLoginService = new LoginService(aNick, aPassword);
        AUser aU = aLoginService.getUserFromDatabase();
        if(aU != null)
        {
            dataMsg=aU.toString();
        }
        return Response.ok(dataMsg).build();
    }
    
    
    
    @Path("test/{nick}-{password}")
    @GET
    public Response userExistGet(@PathParam("nick") String aNick, @PathParam("password") String aPassword)
    {
        String dataMsg ="No exist";
        aLoginService = new LoginService(aNick, aPassword);
        AUser aU = aLoginService.getUserFromDatabase();
        if(aU != null)
        {
            dataMsg=aU.toString();
        }
        return Response.ok(dataMsg).build();
    }
    
  
}
