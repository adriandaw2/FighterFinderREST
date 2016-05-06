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

/**
 *
 * @author Alumne
 */
public class AUserADO {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String BD_URL = "jdbc:mysql://localhost:3306/fighterfinderdb";
    static final String SERVER_NAME = "localhost";
    static final String DB_NAME = "fighterfinderdb";
    static final String USUARI = "standuser";
    static final String PASSWORD = "normal4321@";
    static final int PORT = 3306;
    private MysqlDataSource dataSource;
    
    //SQL SENTENCES
    static final String GET_USER = "SELECT * FROM `user` WHERE `nick` = ? AND `password` = ?";
    static final String ADD_USER = "INSERT INTO `user` (nick, email, password) VALUES (?, ?, ?)";
    static final String CHECK_USER_NICK = "SELECT nick FROM `user` WHERE nick = ?";
    static final String CHECK_USER_EMAIL = "SELECT email FROM `user` WHERE email = ?";
    static final String CHECK_USER_NICK_EMAIL = "SELECT nick, email FROM `user` WHERE nick = ? OR email = ?";
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
                aU.setIdProfile(rs.getInt(5));
                aU.setIdObjective(rs.getInt(6));
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
}
