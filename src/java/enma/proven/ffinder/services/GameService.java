/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.services;

import enma.proven.ffinder.entities.AGame;
import enma.proven.ffinder.entities.persistence.AGameADO;
import java.util.List;

/**
 *
 * @author Alumne
 */
public class GameService {
    private AGameADO myADO;

    public GameService() {
        myADO = new AGameADO();
    }
    /**
     * getAllGamesFromDatabase
     * Function to call the ADO and return a list of all the games
     * @return List<AGame>
     */
    public List<AGame> getAllGamesFromDatabase()
    {
        List<AGame> aGameList = myADO.getAllGamesFromDatabase();
        
        return aGameList;
     }
}
