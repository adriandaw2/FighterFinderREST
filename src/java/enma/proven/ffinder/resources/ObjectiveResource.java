/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.resources;

import com.google.gson.Gson;
import enma.proven.ffinder.entities.AObjective;
import enma.proven.ffinder.entities.AUser;
import enma.proven.ffinder.services.ObjectiveService;
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
@Path("objective")
public class ObjectiveResource {
    ObjectiveService aObjService;

    public ObjectiveResource() {
    }

    public ObjectiveResource(@Context ServletContext context) {
    }
    
    
    /**
     * getAllObjectives
     * Function to get all the objectives
     * @return Response
     */
    @POST
    @Path("getAllObj")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllObjectives()
    {
        Gson gson = new Gson();
        aObjService = new ObjectiveService();
        HashMap<String, List<AObjective>> aMap = new HashMap();
        List<AObjective> aList = aObjService.getAllObjectives();
        aMap.put("allObjectives", aList);
        String jsonString = gson.toJson(aMap);
        return Response.ok().entity(jsonString).build();
    }
    
    /**
     * addNewObjective
     * Function to add a new objective
     * @param objMsg
     * @return Response
     */
    @POST
    @Path("addNewObj")
    public Response addNewObjective(@FormParam("objmsg")String objMsg)
    {
        aObjService = new ObjectiveService();
        int result = aObjService.addNewObjective(objMsg);
        return Response.ok(result).build();
    }
    
    /**
     * modObjective
     * Function to add a new objective
     * @param objMsg
     * @return Response
     */
    @POST
    @Path("modObjective")
    public Response modObjective(@FormParam("objmsg")String objMsg, @FormParam("oid")int oID)
    {
        aObjService = new ObjectiveService();
        int result = aObjService.modObjective(objMsg, oID);
        return Response.ok(result).build();
    }
    
}
