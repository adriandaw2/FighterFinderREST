/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.entities.persistence;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import enma.proven.ffinder.entities.ACharacter;
import enma.proven.ffinder.entities.AGame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian
 */
public class ACharacterADO {
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
    static final String GET_GAME_CHARACTERS = "SELECT * FROM `character` WHERE id_game = ?";
    static final String GET_GAME_CHARACTER_USER = "SELECT c.id, c.name FROM `character` c LEFT OUTER JOIN `user_character` uc ON c.id NOT IN (SELECT ucs.character_id FROM `user_character` ucs WHERE ucs.user_id = ?) WHERE c.id_game = ?";
    //static final String GET_USER_CHARACTERS= "SELECT * FROM `user_`"
    static final String ADD_CHARACTER_TO_USER = "INSERT INTO `user_character` (user_id, character_id) VALUES (?, ?)";
    static final String DELETE_CHARACTER_USER = "DELETE FROM `user_character` WHERE user_id = ? AND character_id = ?";

    public ACharacterADO() {
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
     * getAllCharacterFromGame
     * Function that returns all the characters from one game
     * @param aGameID
     * @param aUserID
     * @return List<ACharacter>
     */
    public List<ACharacter> getAllCharacterFromGame(int aGameID, int aUserID)
    {
        List<ACharacter> aCharList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ACharacter aCh = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_GAME_CHARACTER_USER);
            pstmt.setInt(1, aUserID);
            pstmt.setInt(2, aGameID);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                aCh = new ACharacter(rs.getInt(1), rs.getString(2), rs.getInt(3));
                aCharList.add(aCh);
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace(System.out);
        }
        return aCharList;
    }
    
    
    /**
     * addCharacterToUser
     * Function that will return an 1 if the character had been added correctly, 0 if not, and -1 if something happened in the DDBB
     * @param uID
     * @param cID
     * @return int
     */
    public int addCharacterToUser(int uID, int cID)
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(ADD_CHARACTER_TO_USER);
            pstmt.setInt(1, uID);
            pstmt.setInt(2, cID);
            result = pstmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
            result = -1;
        }
        finally{
                try {
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Could not close all the DB stuff");
                } 
            }
        return result;
    }
    
    
    /**
     * deleteCharacterUser
     * Function to delete a character from the player
     * @param uID
     * @param cID
     * @return int
     */
    public int deleteCharacterUser(int uID, int cID)
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(DELETE_CHARACTER_USER);
            pstmt.setInt(1, uID);
            pstmt.setInt(2, cID);
            result = pstmt.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
            result = -1;
        }
        finally{
                try {
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Could not close all the DB stuff");
                } 
            }
        return result;
    }
}
