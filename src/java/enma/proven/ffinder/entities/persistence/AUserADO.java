/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enma.proven.ffinder.entities.persistence;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import enma.proven.ffinder.entities.AUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alumne
 */
public class AUserADO {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    //static final String BD_URL = "jdbc:mysql://localhost:3306/fighterfinderdb";
    static final String BD_URL = "jdbc:mysql://localhost/dam16g4";
    static final String SERVER_NAME = "localhost";
    //static final String DB_NAME = "fighterfinderdb";
    static final String DB_NAME = "dam16g4";
    //static final String USUARI = "standuser";
    static final String USUARI = "dam16-g4";
    //static final String PASSWORD = "normal4321@";
    static final String PASSWORD = "Oz5eim";
    static final int PORT = 3306;
    private MysqlDataSource dataSource;
    
    //SQL SENTENCES
    static final String GET_USER = "SELECT * FROM `user` WHERE `nick` = ? AND `password` = ?";
    static final String GET_USER_BY_NICK_LIKE = "SELECT `user`.id, `user`.nick, `user`.skill, `user`.ubication, `user`.id_profile, `user`.avaible, `user`.showinmap, `user`.glat, `user`.glon, `objective`.message FROM `user` INNER JOIN `objective` ON `user`.id_objective=`objective`.id WHERE `user`.nick LIKE ?";
    static final String GET_USER_BY_GAME = "SELECT DISTINCT u.id, u.nick, u.skill, u.ubication, u.id_profile, u.avaible, u.showinmap, u.glat, u.glon, u.id_objective FROM `user` u LEFT JOIN `user_game` ug ON u.id IN (SELECT ugs.user_id FROM `user_game` ugs WHERE ugs.game_id = ?)";
    static final String ADD_USER = "INSERT INTO `user` (nick, email, password) VALUES (?, ?, ?)";
    static final String CHECK_USER_NICK = "SELECT nick FROM `user` WHERE nick = ?";
    static final String CHECK_USER_EMAIL = "SELECT email FROM `user` WHERE email = ?";
    static final String CHECK_USER_NICK_EMAIL = "SELECT nick, email FROM `user` WHERE nick = ? OR email = ?";
    static final String MOD_USER = "UPDATE `user` SET nick = ?, password = ?, id_objective = ? WHERE id = ?";
    static final String ADD_USER_TO_FAV = "INSERT INTO `user_user_fav` (user_id, user_added_fav) VALUES (?, ?)";
    static final String DELETE_USER_FROM_FAV = "DELETE FROM `user_user_fav` WHERE user_id = ? AND user_added_fav = ?";
    static final String GET_USER_FAVS = "SELECT DISTINCT u.id, u.nick, u.skill, u.ubication, u.id_profile, u.avaible, u.showinmap, u.glat, u.glon, u.id_objective FROM `user` u INNER JOIN `user_user_fav` uf ON u.id IN (SELECT ufs.user_added_fav FROM `user_user_fav` ufs WHERE ufs.user_id = ?)";
    //EMAIL STUFF
    static final String CHECK_USER_AVAIBLE = "SELECT avaible FROM `user` WHERE email = ?";
    static final String ACTIVATE_ACC = "UPDATE `user` SET avaible = 1 WHERE email = ?";
    static final String DEACTIVATE_ACC = "UPDATE `user` SET avaible = 0 WHERE email = ?";
    public AUserADO() {
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
                aU.setPassword(rs.getString(4));
                aU.setUbication(rs.getString(5));
                aU.setSkill(rs.getInt(6));
                aU.setAvaible(rs.getBoolean(7));
                aU.setShowinmap(rs.getBoolean(8));
                aU.setGlat(rs.getFloat(9));
                aU.setGlon(rs.getFloat(10));
                aU.setIdProfile(rs.getInt(11));
                aU.setIdObjective(rs.getInt(12));
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Could not close all the DB stuff");
            } 
        }
        /*aU = new AUser();
        aU.setId(1);
        aU.setNick(aNick);
        aU.setIdProfile(1);*/
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
            }
            finally{
                try {
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Could not close all the DB stuff");
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
                pstmt.setInt(3, aUser.getIdObjective());
                pstmt.setInt(4, aUser.getId());
                aRes = pstmt.executeUpdate();
            }catch(SQLException ex)
            {
                ex.printStackTrace(System.out);
            }
            finally{
                try {
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Could not close all the DB stuff");
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
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Could not close all the DB stuff");
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
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Could not close all the DB stuff");
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
            ex.printStackTrace(System.out);
            result = -1;
        }finally{
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Could not close all the DB stuff");
                result = -1;
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
            ex.printStackTrace(System.out);
            result = -1;
        }finally{
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Could not close all the DB stuff");
                result = -1;
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
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Could not close all the DB stuff");
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
        }finally{
            try{
                conn.close();
                pstmt.close();
            }catch(SQLException ex)
            {
                System.out.println("Could not close all the DB stuff");
                result = -1;
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
        }finally{
            try{
                conn.close();
                pstmt.close();
            }catch(SQLException ex)
            {
                System.out.println("Could not close all the DB stuff");
                result = -1;
            }
        }
        return result;
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
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Could not close all the DB stuff");
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
            ex.printStackTrace(System.out);
            aRes = -1;
        }
        finally{
            try {
                pstmt.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Could not close all the DB stuff");
            } 
        }
        return aRes;
    }
}
