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
    
    /**
     * getGamesUserDontPlayFromDatabase
     * Function to call the ADO and return a list of all the games the user don't play
     * @param uID
     * @return List<AGame>
     */
    public List<AGame> getGamesUserDontPlayFromDatabase(int uID)
    {
        List<AGame> aGameList = myADO.getGamesUserDontPlayFromDatabase(uID);
        
        return aGameList;
    }
    
    /**
     * addGameToUser
     * Function to add a game to a user.
     * @param uID
     * @param gID
     * @return int
     */
    public int addGameToUser(int uID, int gID)
    {
        int result = myADO.addGameToPlayer(uID, gID);
        
        return result;
    }
}
