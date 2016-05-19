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
     * getAllObjectives
     * Function to get all the objectives, this function MUST return always a list of objectives
     * return List<AObjective>
     */
    public List<AObjective> getAllObjectives()
    {
        List<AObjective> aList = this.myADO.getAllobjectives();
        
        return aList;
    }
    
}
