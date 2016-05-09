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
     * getAllCharacterFromGame
     * Function to get all the character from one game
     * @param aGameID
     * @param aUserID
     * @return List<ACharacter>
     */
    public List<ACharacter> getAllCharacterFromGame(int aGameID, int aUserID)
    {
        List<ACharacter> aCharList = this.myADO.getAllCharacterFromGame(aGameID, aUserID);
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
    
    
    
    
}
