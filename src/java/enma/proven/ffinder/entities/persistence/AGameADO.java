/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.entities.persistence;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import enma.proven.ffinder.entities.AGame;
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
public class AGameADO {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String BD_URL = "jdbc:mysql://localhost:3306/fighterfinderdb";
    static final String SERVER_NAME = "localhost";
    static final String DB_NAME = "fighterfinderdb";
    static final String USUARI = "standuser";
    static final String PASSWORD = "normal4321@";
    static final int PORT = 3306;
    private MysqlDataSource dataSource;
    
    //SQL SENTENCES
    static final String GET_ALL_GAMES= "SELECT * FROM game;";
    static final String GET_GAMES_USER_DONT_PLAY = "";
    static final String ADD_GAME_TO_USER = "INSERT INTO `user_game` (user_id, game_id) VALUES (?, ?)";

    public AGameADO() {
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
     * getAllGamesFromDatabase
     * Function that returns all the games from the database
     * @return List<AGame>
     */
    public List<AGame> getAllGamesFromDatabase()
    {
        ArrayList<AGame> aGameList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AGame aG = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_ALL_GAMES);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                aG = new AGame(rs.getInt(1), rs.getString(2));
                aGameList.add(aG);
            }
        }catch(SQLException ex)
        {
            
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
        return aGameList;
    }
    
    /**
     * getGamesUserDontPlayFromDatabase
     * Function that returns all the games from the database the user don't play
     * @param uID
     * @return List<AGame>
     */
    public List<AGame> getGamesUserDontPlayFromDatabase(int uID)
    {
        ArrayList<AGame> aGameList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AGame aG = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_GAMES_USER_DONT_PLAY);
            pstmt.setInt(1, uID);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                aG = new AGame(rs.getInt(1), rs.getString(2));
                aGameList.add(aG);
            }
        }catch(SQLException ex)
        {
            
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
        return aGameList;
    }
    
    
    /**
     * addGameToPlayer
     * This function will add a game to the player. If the game is added correctly, it will return a 1, if not, it will return a 0,
     * a -1 will be returned if the server part fails to try it.
     * @param uID
     * @param gID
     * @return result
     */
    public int addGameToPlayer(int uID, int gID)
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        //check if the user is playing already the game
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(ADD_GAME_TO_USER);
            pstmt.setInt(1, uID);
            pstmt.setInt(2, gID);
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
