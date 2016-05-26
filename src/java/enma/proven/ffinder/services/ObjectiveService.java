/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.services;

import enma.proven.ffinder.entities.AObjective;
import enma.proven.ffinder.entities.persistence.AObjectiveADO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alumne
 */
public class ObjectiveService {
    private AObjectiveADO myADO;

    public ObjectiveService() {
        myADO = new AObjectiveADO();
    }

    /**
     * CreateADO
     * Method to create and instanciate AUserADO
     */
    private void CreateADO()
    {
        this.myADO = new AObjectiveADO();
    }
    
    
    /**
     * getOneObjective
     * Function to get one objective info
     * @param oID
     * @return AObjective
     */
    public AObjective getOneObjective(int oID)
    {
        AObjective aO = this.myADO.getOneObjective(oID);
        
        return aO;
    }
    /**
     * getAllObjectives
     * Function to get all the objectives, this function MUST return always a list of objectives
     * return List<AObjective>
     */
    public List<AObjective> getAllObjectives()
    {
        List<AObjective> aList = this.myADO.getAllobjectives();
        
        return aList;
    }
    
    /**
     * addNewObjective
     * Function to add a new objective
     * @param objMsg
     * @return int
     */
    public int addNewObjective(String objMsg)
    {
        int result = this.myADO.addNewObjective(objMsg);
        return result;
    }
    
    /**
     * modObjective
     * Function to mod a objective
     * @param objMsg
     * @param oID
     * @return int
     */
    public int modObjective(String objMsg, int oID)
    {
        int result = this.myADO.modifyObjective(objMsg, oID);
        return result;
    }
}
