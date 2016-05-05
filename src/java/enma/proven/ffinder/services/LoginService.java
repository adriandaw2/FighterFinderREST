/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enma.proven.ffinder.services;
import enma.proven.ffinder.entities.AUser;
import enma.proven.ffinder.entities.persistence.AUserADO;

/**
 *
 * @author Alumne
 */
public class LoginService {
    
    private String nick;
    private String password;
    private AUserADO myADO;
    public LoginService() {
        CreateADO();
    }

    public LoginService(String nick, String password) {
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
    
    
}
