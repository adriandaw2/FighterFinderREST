/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enma.proven.ffinder.services;
import enma.proven.ffinder.entities.AUser;
import enma.proven.ffinder.entities.persistence.AUserADO;
import java.util.List;

/**
 *
 * @author Alumne
 */
public class UserService {
    
    private String nick;
    private String password;
    private AUserADO myADO;
    public UserService() {
        CreateADO();
    }

    public UserService(String nick, String password) {
        this.nick = nick;
        this.password = password;
        CreateADO();
    }
    
    /**
     * CreateADO
     * Method to create and instanciate AUserADO
     */
    private void CreateADO()
    {
        myADO = new AUserADO();
    }
    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * getUserFromDatabase
     * Function to get the user in the database
     * @return AUser
     */
    public AUser getUserFromDatabase()
    {
        AUser aU = null;
        aU = myADO.getUserFromDatabase(this.nick, this.password);
        return aU;
    }
    
    /**
     * addUserToDatabase
     * Function to call the ADO and add a new user tot he database
     * will return the affected colummns
     * @param nU
     * @return int
     */
    public int addUserToDatabase(AUser nU)
    {
        int result = myADO.addUserToDatabase(nU);
        //create email class and use send method
        return result;
    }
    
    /**
     * modifyUser
     * Function to modify the user in the DDBB
     * @param aUser
     * @param currentNick
     * @return in
     */
    public int modifyUser(AUser aUser, String currentNick)
    {
        int result = this.myADO.modifyUserInDatabase(aUser, currentNick);
        
        return result;
    }
    
    /**
     * searchUserByNickname
     * Function to get all the results of the searhc of one nickname
     * @param nickToSearch
     * @return List<AUser>
     */
    public List<AUser> searchUserByNickname(String nickToSearch)
    {
        List<AUser> aList = this.myADO.searchUsersByNickname(nickToSearch);
        
        return aList;
    }
    
    /**
     * searchUserByNickname
     * Function to get all the results of the search of all the user that play one game
     * @param gID
     * @return List<AUser>
     */
    public List<AUser> searchUserByGame(int gID)
    {
        List<AUser> aList = this.myADO.searchUserByGame(gID);
        
        return aList;
    }
}
