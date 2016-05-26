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
     * getOneGameInfo
     * Function to get one game info
     * @param gID
     * @return AGame
     */
    public AGame getOneGameInfo(int gID)
    {
        AGame aG = this.myADO.getOneGameInfo(gID);
        
        return aG;
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
     * getGamesUserPlayFromDatabase
     * Function to call the ADO and return a list of all the games the user play
     * @param uID
     * @return List<AGame>
     */
    public List<AGame> getGamesUserPlayFromDatabase(int uID)
    {
        List<AGame> aGameList = myADO.getGamesUserPlayFromDatabase(uID);
        
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
    
    
    /**
     * deleteGameFromUser
     * Function to delete a game from one user
     * @param uID
     * @param gID
     * @return int
     */
    public int deleteGameFromUser(int uID, int gID)
    {
        int result = this.myADO.removeGameFromPlayer(uID, gID);
        
        return result;
    }
    
    /**
     * addGameToDatabase
     * Add a new game to the database
     * @param gName
     * @return int
     */
    public int addGameToDatabase(String gName)
    {
        int result = this.myADO.addNewGame(gName);
        
        return result;
    }
    
    /**
     * modGameFromDatabase
     * Modify a game in the database
     * @param gNameToMod
     * @param gID
     * @return int
     */
    public int modGameFromDatabase(String gNameToMod, int gID)
    {
        int result = this.myADO.modGame(gNameToMod, gID);
        return result;
    }
}   
