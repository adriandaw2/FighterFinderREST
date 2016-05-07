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
     * @param int
     * @return List<ACharacter>
     */
    public List<ACharacter> getAllCharacterFromGame(int aGameID)
    {
        List<ACharacter> aCharList = this.myADO.getAllCharacterFromGame(aGameID);
        return aCharList;
    }
    
    
    /**
     * addCharacerToUser
     */
    
    
    
    
}
