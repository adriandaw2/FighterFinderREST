/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enma.proven.ffinder.services;
import enma.proven.ffinder.entities.AEmail;
import enma.proven.ffinder.entities.AUser;
import enma.proven.ffinder.entities.persistence.AUserADO;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

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
        if(result == 1)
        {
            //create email class and use send method
            AEmail aMail = new AEmail(nU.getEmail());
            aMail.sendEmailConfirmation();
        }
        return result;
    }
    
    
    /**
     * sendActivationEmail
     * Method to send an activation email
     * @param aEmail
     * @return int
     */
    public int sendActivationEmail(String aEmail)
    {
        int result = this.myADO.checkUserExistByEmail(aEmail);
        if(result == 1)
        {
            AEmail aMail = new AEmail(aEmail);
            aMail.sendEmailConfirmation();
        }
        return result;
    }
    
    /**
     * sendDeactivationEmail
     * Method to send an deactivation email
     * @param aEmail
     * @return int
     */
    public int sendDeactivationEmail(String aEmail)
    {
        int result = this.myADO.checkUserExistByEmail(aEmail);
        if(result == 1)
        {
            AEmail aMail = new AEmail(aEmail);
            aMail.sendEmailDeactivation();
        }
        return result;
    }
    
    /**
     * sendEmailChangePassword
     * Function to send a email with a random generated password
     * Used when a user don't remember the password or the nickname
     * @param uMail
     * @return int
     */
    public int sendEmailChangePassword(String uMail)
    {
        String randPass = generateRandomPassword();
        //password in MD5
        String randPassMD5 = getMD5(randPass);
        int result = this.myADO.changeUserPasswordByEmail(uMail, randPassMD5);
        AUser aUser = null;
        //change the method to use the two password
        //the randPass to send the pass via email and the MD5 to insert the pass in the DDBB
        
        if(result == 1)
        {
            aUser = this.myADO.getUserByEmail(uMail);
            //send email with the information
            AEmail mail = new AEmail(aUser.getEmail());
            mail.sendEmailRandomPassword(aUser.getNick(), randPass);
        }
        
        return result;
    }
    
    
    /**
     * changeCurrentPassword
     * Function to change the current password
     * @param uID
     * @param newPass
     * @return int
     */
    public int changeCurrentPassword(int uID, String newPass)
    {
        int result = this.myADO.changeCurrentPassword(uID, newPass);
        
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
    
    
    /**
     * activateUserAccount
     * Function to activate the user account
     * @param uMail
     * @return int
     */
    public int activateUserAccount(String uMail)
    {
        int result = this.myADO.activateUserAccountDDBB(uMail);
        
        return result;
    }
    
    /**
     * deactivateUserAccount
     * Function to deactivate the user account
     * @param uMail
     * @return int
     */
    public int deactivateUserAccount(String uMail)
    {
        int result = this.myADO.deactivateUserAccountDDBB(uMail);
        
        return result;
    }
    
    /**
     * getAllUserFav
     * Function to get all the user fabs
     * @param uID
     * return List<AUser>
     */
    public List<AUser> getAllUserFav(int uID)
    {
        List<AUser> aList = this.myADO.getAllUserFavs(uID);
        
        return aList;
    }
    
    /**
     * addUserToFav
     * Function to add a user to a user fav
     * @param uID
     * @param uToAddID
     * @return int
     */
    public int addUserToFav(int uID, int uToAddID)
    {
        int result = this.myADO.addUserToFav(uID, uToAddID);
        
        return result;
    }
    
    /**
     * deleteuserFromFav
     * Function to delete a user from a user fav
     * @param uID
     * @param uToAddID
     * @return int
     */
    public int deleteuserFromFav(int uID, int uToAddID)
    {
        int result = this.myADO.deleteUserFromFav(uID, uToAddID);
        
        return result;
    }
    
    /**
     * searchUSkillSame
     * Function to search a user by skill leve
     * @param sLevel
     * @return List<AUser>
     */
    public List<AUser> searchUSkillSame(int sLevel)
    {
        List<AUser> aList = this.myADO.getUSkillSame(sLevel);
        
        return aList;
    }
    
    /**
     * rateUSkill
     * Function to rate user skill
     * @param uWhoRate
     * @param uRated
     * @param skillRate
     * @return int
     */
    public int rateUSkill(int uWhoRate, int uRated, int skillRate)
    {
        int result = this.myADO.rateUserSkill(uWhoRate, uRated, skillRate);
        
        return result;
    }
    
    /**
     * generateRandomPassword
     * Function that return a random password
     * @return String
     */
    private String generateRandomPassword()
    {
        String charsForRandom = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rng = new Random();
        char[] text = new char[6];
        for (int i = 0; i < 6; i++)
        {
            text[i] = charsForRandom.charAt(rng.nextInt(charsForRandom.length()));
        }
        return new String(text);
    }
    
    /**
     * getMD5
     * Function to get a md5 hash from a string
     * @param sToMD5
     * @return String
     */
    private String getMD5(String sToMD5)
    {
        String toMD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(sToMD5.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            toMD5 = hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return toMD5;
    }
}
