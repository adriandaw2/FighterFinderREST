/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.services;

import enma.proven.ffinder.entities.ACharacter;
import enma.proven.ffinder.entities.persistence.ACharacterADO;
import java.util.List;

/**
 *
 * @author Adrian
 */
public class CharacterService {
    private ACharacterADO myADO;

    public CharacterService() {
        this.myADO = new ACharacterADO();
    }
    
    /**
     * getAllCharacterUserUseFromGame
     * Function to get all the character from one game
     * @param aGameID
     * @param aUserID
     * @return List<ACharacter>
     */
    public List<ACharacter> getAllCharacterUserUseFromGame(int aGameID, int aUserID)
    {
        List<ACharacter> aCharList = this.myADO.getAllCharacterUserUseFromGame(aGameID, aUserID);
        return aCharList;
    }
    
    /**
     * getAllCharacterUserNoUseFromGame
     * Function to get all the character from one game
     * @param aGameID
     * @param aUserID
     * @return List<ACharacter>
     */
    public List<ACharacter> getAllCharacterUserNoUseFromGame(int aGameID, int aUserID)
    {
        List<ACharacter> aCharList = this.myADO.getAllCharacterUserNoUseFromGame(aGameID, aUserID);
        return aCharList;
    }
    
    
    /**
     * addCharacerToUser
     * Function to know if the character is added
     * @param uID
     * @param cID
     * @return int
     */
    public int addCharacerToUser(int uID, int cID)
    {
        int result = this.myADO.addCharacterToUser(uID, cID);
        
        return result;
    }
    
    
    /**
     * deleteCHaracterFromUser
     * Function to know if the delete SQL worked correctly
     * @param uID
     * @param cID
     * @return int
     */
    public int deleteCHaracterFromUser(int uID, int cID)
    {
        int result = this.myADO.deleteCharacterUser(uID, cID);
        
        return result;
    }
    
    /**
     * addCharacterToGame
     * Function to add a character to a  game
     * @param cName
     * @param gID
     * @return int
     */
    public int addCharacterToGame(String cName, int gID)
    {
        int result = this.myADO.addNewCharacterToGame(cName, gID);
        return result;
    }
    
    /**
     * modCharacterFromGame
     * Function to add a character to a  game
     * @param cName
     * @param gID
     * @return int
     */
    public int modCharacterFromGame(String cName, int gID)
    {
        int result = this.myADO.modCharacter(cName, gID);
        return result;
    }
    
    
    
}
