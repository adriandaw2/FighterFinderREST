/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enma.proven.ffinder.entities.persistence;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import enma.proven.ffinder.entities.AUser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Alumne
 */
public class AUserADO {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String SERVER_NAME = "localhost";
    static final int PORT = 3306;
    //local
    static final String BD_URL = "jdbc:mysql://localhost:3306/fighterfinderdb";
    static final String USUARI = "standuser";
    static final String PASSWORD = "normal4321@";
    static final String DB_NAME = "fighterfinderdb";
    
    //school server
    /*static final String BD_URL = "jdbc:mysql://localhost/dam16g4";
    static final String DB_NAME = "dam16g4";
    static final String USUARI = "dam16-g4";
    static final String PASSWORD = "Oz5eim";*/
    private MysqlDataSource dataSource;
    
    //SQL SENTENCES
    static final String GET_USER = "SELECT * FROM `user` WHERE `nick` = ? AND `password` = ?";
    static final String GET_USER_BY_NICK_LIKE = "SELECT `user`.id, `user`.nick, `user`.skill, `user`.ubication, `user`.id_profile, `user`.avaible, `user`.showinmap, `user`.glat, `user`.glon, `objective`.message FROM `user` INNER JOIN `objective` ON `user`.id_objective=`objective`.id WHERE `user`.nick LIKE ? ORDER BY `user`.nick";
    static final String GET_USER_BY_GAME = "SELECT DISTINCT u.id, u.nick, u.skill, u.ubication, u.id_profile, u.avaible, u.showinmap, u.glat, u.glon, u.id_objective FROM `user` u LEFT JOIN `user_game` ug ON u.id IN (SELECT ugs.user_id FROM `user_game` ugs WHERE ugs.game_id = ?) ORDER BY u.nick";
    static final String ADD_USER = "INSERT INTO `user` (nick, email, password) VALUES (?, ?, ?)";
    static final String CHECK_USER_NICK = "SELECT nick FROM `user` WHERE nick = ?";
    static final String CHECK_USER_EMAIL = "SELECT email FROM `user` WHERE email = ?";
    static final String CHECK_USER_NICK_EMAIL = "SELECT nick, email FROM `user` WHERE nick = ? OR email = ?";
    static final String MOD_USER = "UPDATE `user` SET nick = ?, password = ?, ubication = ?, id_objective = ? WHERE id = ?";
    static final String CHANGE_USER_PASS_EMAIL = "UPDATE `user` SET password = ? WHERE email = ?";
    static final String CHANGE_CURRENT_USER_PASS = "UPDATE `user` SET password = ? WHERE id = ?";
    static final String ADD_USER_TO_FAV = "INSERT INTO `user_user_fav` (user_id, user_added_fav) VALUES (?, ?)";
    static final String DELETE_USER_FROM_FAV = "DELETE FROM `user_user_fav` WHERE user_id = ? AND user_added_fav = ?";
    static final String GET_USER_FAVS = "SELECT DISTINCT u.id, u.nick, u.skill, u.ubication, u.id_profile, u.avaible, u.showinmap, u.glat, u.glon, u.id_objective FROM `user` u INNER JOIN `user_user_fav` uf ON u.id IN (SELECT ufs.user_added_fav FROM `user_user_fav` ufs WHERE ufs.user_id = ?) ORDER BY u.nick";
    static final String GET_USER_BY_SKILL = "SELECT * FROM `user` WHERE skill = ? ORDER BY nick";
    static final String GET_USER_BY_SKILL_GREATER_SAME = "SELECT * FROM `user` WHERE skill >= ? ORDER BY nick";
    static final String GET_USER_BY_SKILL_LOWER_SAME = "SELECT * FROM `user` WHERE skill <= ? ORDER BY nick";
    //EMAIL STUFF
    static final String CHECK_USER_AVAIBLE = "SELECT avaible FROM `user` WHERE email = ?";
    static final String ACTIVATE_ACC = "UPDATE `user` SET avaible = 1 WHERE email = ?";
    static final String DEACTIVATE_ACC = "UPDATE `user` SET avaible = 0 WHERE email = ?";
    static final String GET_USER_BY_EMAIL = "SELECT id, nick, email FROM `user` WHERE email = ?";
    private Logger myLogger;
    public AUserADO() {
        //createLogger();
        prepareAndSetConection();
    }
    
    
    
    
    /**
     * prepareAndSetConection
     * Method to set the DataSource with the values instanciated at the beggining of the class
     */
    private void prepareAndSetConection()
    {
        dataSource = new MysqlDataSource();
        dataSource.setUser(USUARI);
        dataSource.setPassword(PASSWORD);
        dataSource.setServerName(SERVER_NAME);
        dataSource.setPort(PORT);
        dataSource.setDatabaseName(DB_NAME);
    }
    
    /**
     * getUserFromDatabase
     * Function that return a User from the database
     * @param aNick
     * @param aPassword
     * @return AUser
     */
    public AUser getUserFromDatabase(String aNick, String aPassword)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AUser aU = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_USER);
            pstmt.setString(1, aNick);
            pstmt.setString(2, aPassword);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                aU = new AUser();
                aU.setId(rs.getInt(1));
                aU.setNick(rs.getString(2));
                aU.setEmail(rs.getString(3));
                //aU.setPassword(rs.getString(4));
                aU.setUbication(rs.getString(5));
                aU.setSkill(rs.getInt(6));
                aU.setAvaible(rs.getBoolean(7));
                aU.setShowinmap(rs.getBoolean(8));
                aU.setGlat(rs.getFloat(9));
                aU.setGlon(rs.getFloat(10));
                aU.setIdProfile(rs.getInt(11));
                aU.setIdObjective(rs.getInt(12));
            }
            if(aU == null)
            {
                //write log
                myLogger.log(Level.INFO, "User with nick: {0} failed to enter", aNick);
                //System.out.println("User don't exist");
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace(System.out);
            //write log
            myLogger.log(Level.SEVERE, "Exception trying to log in: {0}", ex.getMessage());
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Could not close all the DB stuff");
                //write log
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            } 
        }
        return aU;
    }
    
    
    /**
     * addUserToDatabase
     * Function to add the user to a database
     * @param uToAdd
     * @return int
     */
    public int addUserToDatabase(AUser uToAdd)
    {
        int aRes  = checkIfUserExistToAdd(uToAdd);
        Connection conn = null;
        PreparedStatement pstmt = null;
        if(aRes == 0)
        {
            try{
                conn = dataSource.getConnection();
                pstmt = conn.prepareStatement(ADD_USER);
                pstmt.setString(1, uToAdd.getNick());
                pstmt.setString(2, uToAdd.getEmail());
                pstmt.setString(3, uToAdd.getPassword());
                aRes = pstmt.executeUpdate();
            }catch(SQLException ex)
            {
                ex.printStackTrace(System.out);
                //write log
                myLogger.log(Level.SEVERE, "Exception trying to add user to database: {0}", ex.getMessage());
            }
            finally{
                try {
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    //System.out.println("Could not close all the DB stuff");
                    //write log
                    myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
                } 
            }
        }
        return aRes;
    }
    
    
    
    /**
     * modifyUserInDatabase
     * Function to modify a user in the database
     * @param aUser
     * @param currentNick
     * @return int
     */
    public int modifyUserInDatabase(AUser aUser, String currentNick) {
        int aRes = checkIfUserExistToAdd(aUser);
        //This is for check if the user didn't change the nickname
        if(aRes == -1 && aUser.getNick().equals(currentNick))
        {
            aRes = 0;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        if(aRes == 0)
        {
            try{
                conn = dataSource.getConnection();
                pstmt = conn.prepareStatement(MOD_USER);
                pstmt.setString(1, aUser.getNick());
                pstmt.setString(2, aUser.getPassword());
                pstmt.setString(3, aUser.getUbication());
                pstmt.setInt(4, aUser.getIdObjective());
                pstmt.setInt(5, aUser.getId());
                aRes = pstmt.executeUpdate();
            }catch(SQLException ex)
            {
                ex.printStackTrace(System.out);
                //write log
                myLogger.log(Level.INFO, "Exception trying to modify a user: {0}", ex.getMessage());
            }
            finally{
                try {
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    //System.out.println("Could not close all the DB stuff");
                    //write log
                    myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
                } 
            }
        }
        
        return aRes;
    }
    
    
    /**
     * searchUsersByNickname
     * Function to get all the user with s search criteria.
     * @param nickToSearch
     * @return List<AUser>
     */
    public List<AUser> searchUsersByNickname(String nickToSearch)
    {
        List<AUser> aList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AUser aU = null;
        //`user`.id, `user`.nick, `user`.skill, `user`.ubication, `user`.id_profile, `user`.avaible, `user`.showinmap, `user`.glat, `user`.glon, `objective`.message
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_USER_BY_NICK_LIKE);
            pstmt.setString(1, "%"+nickToSearch+"%");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                if(rs.getInt(5) != 1)
                {
                    aU = new AUser();
                    aU.setId(rs.getInt(1));
                    aU.setNick(rs.getString(2));
                    aU.setSkill(rs.getInt(3));
                    aU.setUbication(rs.getString(4));
                    aU.setAvaible(rs.getBoolean(6));
                    aU.setShowinmap(rs.getBoolean(7));
                    aU.setGlat(rs.getFloat(8));
                    aU.setGlon(rs.getFloat(9));
                    aU.setObjectiveMsg(rs.getString(10));
                    aList.add(aU);
                }
                
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace(System.out);
            //write log
            myLogger.log(Level.INFO, "Exception trying to search users by nick: {0}", ex.getMessage());
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                //System.out.println("Could not close all the DB stuff");
                //write log
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            } 
        }
        return aList;
    }
    
    /**
     * searchUserByGame
     * This function search all the user by the game they play
     * @param gID
     * @return List<AUser>
     */
    public List<AUser> searchUserByGame(int gID)
    {
        List<AUser> aList = new ArrayList();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AUser aU = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_USER_BY_GAME);
            pstmt.setInt(1, gID);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                if(rs.getInt(5) != 1)
                {
                    
                    aU = new AUser();
                    aU.setId(rs.getInt(1));
                    aU.setNick(rs.getString(2));
                    aU.setSkill(rs.getInt(3));
                    aU.setUbication(rs.getString(4));
                    aU.setAvaible(rs.getBoolean(6));
                    aU.setShowinmap(rs.getBoolean(7));
                    aU.setGlat(rs.getFloat(8));
                    aU.setGlon(rs.getFloat(9));
                    aU.setObjectiveMsg(rs.getString(10));
                    aList.add(aU);
                }
                
            }
        }catch(SQLException ex)
        {
            //ex.printStackTrace(System.out);
            //write log
            myLogger.log(Level.INFO, "Exception trying to search users by game: {0}", ex.getMessage());
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                //System.out.println("Could not close all the DB stuff");
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            } 
        }
        return aList;
    }
    
    
    
    
    
    /**
     * activateUserAccountDDBB
     * Function to activate the user account, will return a 1 if everything is ok,0 if the user is already active or if couldn't update but the database is running, -1 if server error
     * @param uMail
     * @return int
     */
    public int activateUserAccountDDBB(String uMail) {
        int result = checkUserIsActive(uMail);
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(ACTIVATE_ACC);
            pstmt.setString(1, uMail);
            if(result == 0)
            {
                result = pstmt.executeUpdate();
            }
        }catch(SQLException ex)
        {
            //ex.printStackTrace(System.out);
            myLogger.log(Level.INFO, "Exception trying to activate user account: {0}", ex.getMessage());
            result = -1;
        }finally{
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                //System.out.println("Could not close all the DB stuff");
                result = -1;
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            } 
        }
        return result;
    }
    
    /**
     * deactivateUserAccountDDBB
     * Function to deactivate the user account, will return a 1 if everything is ok,2 if the user is already active,
     * 0 if couldn't update but the database is running, -1 if server error
     * @param uMail
     * @return int
     */
    public int deactivateUserAccountDDBB(String uMail) {
        int result = checkUserIsActive(uMail);
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(DEACTIVATE_ACC);
            pstmt.setString(1, uMail);
            //if the user is active then deactive
            if(result == 1)
            {
                result = pstmt.executeUpdate();
            }
        }catch(SQLException ex)
        {
            //ex.printStackTrace(System.out);
            result = -1;
            myLogger.log(Level.INFO, "Exception trying to deactivate user acount: {0}", ex.getMessage());
        }finally{
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                //System.out.println("Could not close all the DB stuff");
                result = -1;
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            } 
        }
        return result;
    }
    
    
    /**
     * getAllUserFavs
     * Function to get all the favorite user of that user
     * @param uID
     * @return List
     */
    public List<AUser> getAllUserFavs(int uID)
    {
        List<AUser> aList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AUser aU = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_USER_FAVS);
            pstmt.setInt(1, uID);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                //if the user is not admin and it's avaible
                int test1 = rs.getInt(5);
                boolean test2 = rs.getBoolean(6);
                if(rs.getInt(5) != 1 && rs.getBoolean(6))
                {
                    
                    aU = new AUser();
                    aU.setId(rs.getInt(1));
                    aU.setNick(rs.getString(2));
                    aU.setSkill(rs.getInt(3));
                    aU.setUbication(rs.getString(4));
                    //aU.setAvaible(rs.getBoolean(6));
                    aU.setShowinmap(rs.getBoolean(7));
                    aU.setGlat(rs.getFloat(8));
                    aU.setGlon(rs.getFloat(9));
                    aU.setIdObjective(rs.getInt(10));
                    aList.add(aU);
                }
                
            }
        }catch(SQLException ex)
        {
            //ex.printStackTrace(System.out);
            myLogger.log(Level.INFO, "Exception trying to get all user favs: {0}", ex.getMessage());
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                //System.out.println("Could not close all the DB stuff");
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            } 
        }
        return aList;
    }
    /**
     * addUserToFav
     * Function to add a user to a user favorites
     * @param uID
     * @param uToAddID
     * @return int
     */
    public int addUserToFav(int uID, int uToAddID)
    {
        //check if relation already exist
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(ADD_USER_TO_FAV);
            pstmt.setInt(1, uID);
            pstmt.setInt(2, uToAddID);
            result = pstmt.executeUpdate();
        }catch(SQLException ex)
        {
            result = -1;
            myLogger.log(Level.INFO, "Exception trying to add a new favorite user: {0}", ex.getMessage());
        }finally{
            try{
                conn.close();
                pstmt.close();
            }catch(SQLException ex)
            {
                //System.out.println("Could not close all the DB stuff");
                result = -1;
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            }
        }
        return result;
    }
    
    /**
     * deleteUserFromFav
     * Function to delete a user from user favorites
     * @param uID
     * @param uToAddID
     * @return int
     */
    public int deleteUserFromFav(int uID, int uToAddID)
    {
        //check if relation already exist
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(DELETE_USER_FROM_FAV);
            pstmt.setInt(1, uID);
            pstmt.setInt(2, uToAddID);
            result = pstmt.executeUpdate();
        }catch(SQLException ex)
        {
            result = -1;
            myLogger.log(Level.INFO, "Exception trying to delete a user favorite: {0}", ex.getMessage());
        }finally{
            try{
                conn.close();
                pstmt.close();
            }catch(SQLException ex)
            {
                //System.out.println("Could not close all the DB stuff");
                result = -1;
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            }
        }
        return result;
    }
    
    /**
     * getUSkillSame
     * This function search all the user with a skill level
     * @param sLevel
     * @return List<AUser>
     */
    public List<AUser> getUSkillSame(int sLevel)
    {
        List<AUser> aList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AUser aU = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_USER_BY_SKILL);
            pstmt.setInt(1, sLevel);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                if(rs.getInt(11)!= 1)
                {
                    //AUser(int id, String nick, String email, String ubication, int skill, boolean avaible, boolean showinmap, float glat, float glon, int idObjective) {
                    aU = new AUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getInt(6), rs.getBoolean(7),
                            rs.getBoolean(8), rs.getFloat(9), rs.getFloat(10), rs.getInt(12));
                    aList.add(aU);
                }
            }
        }catch(SQLException ex)
        {
            myLogger.log(Level.INFO, "Exception trying to get user by same skill level: {0}", ex.getMessage());
        }finally{
            try{
                conn.close();
                pstmt.close();
                rs.close();
            }catch(SQLException ex)
            {
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            }
        }
        return aList;
    }
    
    /**
     * getUSkillSameGreater
     * This function search all the user with the same or greater skill level
     * @param sLevel
     * @return List<AUser>
     */
    public List<AUser> getUSkillSameGreater(int sLevel)
    {
        List<AUser> aList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AUser aU = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_USER_BY_SKILL_GREATER_SAME);
            pstmt.setInt(1, sLevel);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                if(rs.getInt(11)!= 1)
                {
                    //AUser(int id, String nick, String email, String ubication, int skill, boolean avaible, boolean showinmap, float glat, float glon, int idObjective) {
                    aU = new AUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getInt(6), rs.getBoolean(7),
                            rs.getBoolean(8), rs.getFloat(9), rs.getFloat(10), rs.getInt(12));
                    aList.add(aU);
                }
            }
        }catch(SQLException ex)
        {
            myLogger.log(Level.INFO, "Exception trying to get user by same or greater skill level: {0}", ex.getMessage());
        }finally{
            try{
                conn.close();
                pstmt.close();
                rs.close();
            }catch(SQLException ex)
            {
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            }
        }
        return aList;
    }
    
    /**
     * getUSkillSameLower
     * This function search all the user with the same or lower skill level
     * @param sLevel
     * @return List<AUser>
     */
    public List<AUser> getUSkillSameLower(int sLevel)
    {
        List<AUser> aList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AUser aU = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_USER_BY_SKILL_LOWER_SAME);
            pstmt.setInt(1, sLevel);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                if(rs.getInt(11)!= 1)
                {
                    //AUser(int id, String nick, String email, String ubication, int skill, boolean avaible, boolean showinmap, float glat, float glon, int idObjective) {
                    aU = new AUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getInt(6), rs.getBoolean(7),
                            rs.getBoolean(8), rs.getFloat(9), rs.getFloat(10), rs.getInt(12));
                    aList.add(aU);
                }
            }
        }catch(SQLException ex)
        {
            myLogger.log(Level.INFO, "Exception trying to get user by same or lower skill level: {0}", ex.getMessage());
        }finally{
            try{
                conn.close();
                pstmt.close();
                rs.close();
            }catch(SQLException ex)
            {
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            }
        }
        return aList;
    }
    
    //CHECKING METHODS
    
    /**
     * checkIfUserExistToAdd
     * Function to check if the user already exist, will return -1 if nick already exist
     * or -2 if email already exist or -3 if both are already in use
     * @param AUser
     * @return int
     */
    private int checkIfUserExistToAdd(AUser uToCheck)
    {
        int res = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(CHECK_USER_NICK_EMAIL);
            pstmt.setString(1, uToCheck.getNick());
            pstmt.setString(2, uToCheck.getEmail());
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                if(rs.getString(1).equals(uToCheck.getNick()))
                {
                    res = -1;
                }
                if(rs.getString(2).equals(uToCheck.getEmail()))
                {
                    res = -2;
                }
                if((rs.getString(1).equals(uToCheck.getNick()) && rs.getString(2).equals(uToCheck.getEmail())))
                {
                    res = -3;
                }
                if(res != 0)
                {
                    break;
                }
            }
        }catch(SQLException ex)
        {
            //ex.printStackTrace(System.out);
            myLogger.log(Level.INFO, "Exception trying to check if the user already exist to add it: {0}", ex.getMessage());
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                //System.out.println("Could not close all the DB stuff");
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            } 
        }
        return res;
    }
    
    /**
     * checkUserIsActive
     * Function to check if the user is already active
     * @param uMail
     * @return int
     */
    public int checkUserIsActive(String uMail)
    {
        int aRes = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(CHECK_USER_AVAIBLE);
            pstmt.setString(1, uMail);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                aRes = rs.getInt(1);
            }
        }catch(SQLException ex)
        {
            //ex.printStackTrace(System.out);
            aRes = -1;
            myLogger.log(Level.INFO, "Exception trying to check if the user with mail "+uMail+" is already active: {0}",  ex.getMessage());
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                //System.out.println("Could not close all the DB stuff");
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            } 
        }
        return aRes;
    }
    
    /**
     * checkUserExistByEmail
     * Function to check if a user exist with that email
     * @param aEmail
     * @return int
     */
    public int checkUserExistByEmail(String aEmail) {
        int aRes = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_USER_BY_EMAIL);
            pstmt.setString(1, aEmail);
            rs = pstmt.executeQuery();
            aRes = 0;
            while(rs.next())
            {
                aRes = 1;
            }
        }catch(SQLException ex)
        {
            //ex.printStackTrace(System.out);
            aRes = -1;
            myLogger.log(Level.INFO, "Exception trying to check if the user "+aEmail+"exist by email : {0}",  ex.getMessage());
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                //System.out.println("Could not close all the DB stuff");
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            } 
        }
        return aRes;
    }
    
    /**
     * getUserByEmail
     * Function to get the user that just changed the password
     * @param aEmail
     * @return AUser
     */
    public AUser getUserByEmail(String aEmail)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AUser aU = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_USER_BY_EMAIL);
            pstmt.setString(1, aEmail);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                aU = new AUser();
                aU.setNick(rs.getString(2));
                aU.setEmail(rs.getString(3));
            }
        }catch(SQLException ex)
        {
            //ex.printStackTrace(System.out);
            myLogger.log(Level.INFO, "Exception trying to get the user with mail "+aEmail+": {0}",  ex.getMessage());
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                //System.out.println("Could not close all the DB stuff");
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            } 
        }
        return aU;
    }
    
    
    /**
     * changeUserPasswordByEmail
     * Function to change the password in the DDBB, first is a test to see if the password changes, then the password
     * will be in MD5
     * @param uMail
     * @param randPass
     * @return int
     */
    public int changeUserPasswordByEmail(String uMail, String randPass) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(CHANGE_USER_PASS_EMAIL);
            pstmt.setString(1, randPass);
            pstmt.setString(2, uMail);
            result = pstmt.executeUpdate();
        }catch(SQLException ex)
        {
            result = -1;
            myLogger.log(Level.INFO, "Exception trying to change the user with email: "+uMail+": {0}",  ex.getMessage());
        }finally{
            try{
                conn.close();
                pstmt.close();
            }catch(SQLException ex)
            {
                result = -1;
                //System.out.println("Could not close all the DB stuff");
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            }
        }
        return result;
    }
    
    /**
     * changeCurrentPassword
     * Function to change the current password of a user. Uses the ID to identify wich user is
     * @param uID
     * @param newPass
     * @return int
     */
    public int changeCurrentPassword(int uID, String newPass)
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(CHANGE_CURRENT_USER_PASS);
            pstmt.setString(1, newPass);
            pstmt.setInt(2, uID);
            result = pstmt.executeUpdate();
        }catch(SQLException ex)
        {
            result = -1;
            myLogger.log(Level.INFO, "Exception trying to change the current pass of the user with ID: "+uID+": {0}",  ex.getMessage());
        }finally{
            try{
                conn.close();
                pstmt.close();
            }catch(SQLException ex)
            {
                result = -1;
                //System.out.println("Could not close all the DB stuff");
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            }
        }
        return result;
    }
    
    //LOGGER
    /**
     * createLogger
     * Method to instanciate the logger
     * @param none
     * @return none
     */
    private void createLogger() {
        try {
            //FileHandler myFileHandler = new FileHandler("..\\..\\..\\UserADOLog.log", true);
            
            //FileHandler myFileHandler = new FileHandler("logs\\UserADOLog.log", true);
            FileHandler myFileHandler = new FileHandler("D:\\NetBeansProjects\\FighterFinderREST\\src\\java\\enma\\proven\\ffinder\\entities\\persistence\\logs\\UserADOLog.log", true);
            
            myFileHandler.setFormatter(new SimpleFormatter());
            myLogger = Logger.getLogger("enma.proven.ffinder.entities.persistence.logs.UserADOLog.log");
            myLogger.addHandler(myFileHandler);
            myLogger.setUseParentHandlers(false);
        } catch (IOException | SecurityException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
