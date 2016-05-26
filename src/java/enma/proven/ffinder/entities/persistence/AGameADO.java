/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enma.proven.ffinder.entities.persistence;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import enma.proven.ffinder.entities.AGame;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
public class AGameADO {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String SERVER_NAME = "localhost";
    static final int PORT = 3306;
    //local
    /*static final String BD_URL = "jdbc:mysql://localhost:3306/fighterfinderdb";
    static final String USUARI = "standuser";
    static final String PASSWORD = "normal4321@";
    static final String DB_NAME = "fighterfinderdb";*/
    
    //school server
    static final String BD_URL = "jdbc:mysql://localhost/dam16g4";
    static final String DB_NAME = "dam16g4";
    static final String USUARI = "dam16-g4";
    static final String PASSWORD = "Oz5eim";
    private MysqlDataSource dataSource;
    
    //SQL SENTENCES
    static final String GET_ALL_GAMES= "SELECT * FROM game ORDER BY name";
    static final String GET_ONE_GAME = "SELECT * FROM `game` WHERE id = ?";
    static final String GET_GAMES_USER_PLAY = "SELECT DISTINCT g.id, g.name FROM `game` g JOIN `user_game` ug ON g.id IN (SELECT ugs.game_id FROM `user_game` ugs WHERE ugs.user_id = ?) ORDER BY g.name";
    static final String GET_GAMES_USER_DONT_PLAY = "SELECT DISTINCT g.id, g.name FROM `game` g JOIN `user_game` ug ON g.id NOT IN (SELECT ugs.game_id FROM `user_game` ugs WHERE ugs.user_id = ?) ORDER BY g.name";
    static final String ADD_GAME_TO_USER = "INSERT INTO `user_game` (user_id, game_id) VALUES (?, ?)";
    static final String DELETE_GAME_USER = "DELETE FROM `user_game` WHERE user_id = ? AND game_id = ?";
    static final String ADD_NEW_GAME = "INSERT INTO game (name) VALUES (?)";
    static final String UPDATE_GAME_INFO = "UPDATE `game` SET name = ? WHERE id = ?";
    
    private Logger myLogger;
    
    public AGameADO() {
        createLogger();
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
     * getOneGameInfo
     * Function to get the info of one game
     * @param gID
     * @return AGame
     */
    public AGame getOneGameInfo(int gID)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AGame aG = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_ONE_GAME);
            pstmt.setInt(1, gID);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                aG = new AGame(rs.getInt(1), rs.getString(2));
            }
        }catch(SQLException ex)
        {
            //ex.printStackTrace(System.out);
            myLogger.log(Level.INFO, "Exception trying to get all the info from game with ID->"+gID+": {0}", ex.getMessage());
        }
        finally{
                try {
                    pstmt.close();
                    conn.close();
                    rs.close();
                } catch (SQLException ex) {
                    //System.out.println("Could not close all the DB stuff");
                    myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
                } 
            }
        
        return aG;
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
            //ex.printStackTrace(System.out);
            myLogger.log(Level.INFO, "Exception trying to get all the games from database: {0}", ex.getMessage());
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
            //ex.printStackTrace(System.out);
            myLogger.log(Level.INFO, "Exception trying to get all the games a user don't play, user with ID->+"+uID+": {0}", ex.getMessage());
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
        return aGameList;
    }
    
    /**
     * getGamesUserPlayFromDatabase
     * Function that returns all the games from the database the user play
     * @param uID
     * @return List<AGame>
     */
    public List<AGame> getGamesUserPlayFromDatabase(int uID)
    {
        ArrayList<AGame> aGameList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AGame aG = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_GAMES_USER_PLAY);
            pstmt.setInt(1, uID);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                aG = new AGame(rs.getInt(1), rs.getString(2));
                aGameList.add(aG);
            }
        }catch(SQLException ex)
        {
            //ex.printStackTrace(System.out);
            myLogger.log(Level.INFO, "Exception trying to get all the games a user play, with ID->+"+uID+": {0}", ex.getMessage());
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
            //ex.printStackTrace(System.out);
            result = -1;
            myLogger.log(Level.INFO, "Exception trying to add a game to a user: {0}", ex.getMessage());
        }
        finally{
                try {
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    //System.out.println("Could not close all the DB stuff");
                    myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
                } 
            }
        return result;
    }
    
    /**
     * removeGameFromPlayer
     * Functon to delete a game from a player
     * @param gID
     * @param uID
     * @return int
     */
    public int removeGameFromPlayer(int uID, int gID)
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        //check if the user is playing already the game
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(DELETE_GAME_USER);
            pstmt.setInt(1, uID);
            pstmt.setInt(2, gID);
            result = pstmt.executeUpdate();
        }catch(SQLException ex){
            //ex.printStackTrace(System.out);
            result = -1;
            myLogger.log(Level.INFO, "Exception trying to delete a game from a user: {0}", ex.getMessage());
        }
        finally{
                try {
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    //System.out.println("Could not close all the DB stuff");
                    myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
                }
            }
        return result;
    }
    
    /**
     * addNewGame
     * Function to add a new game
     * @param gName
     * @return int
     */
    public int addNewGame(String gName)
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(ADD_NEW_GAME);
            pstmt.setString(1, gName);
            result = pstmt.executeUpdate();
        }catch(SQLException ex){
            //ex.printStackTrace(System.out);
            result = -1;
            myLogger.log(Level.INFO, "Exception trying to add a new game with name->+"+gName+": {0}", ex.getMessage());
        }
        finally{
                try {
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    //System.out.println("Could not close all the DB stuff");
                    myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
                } 
            }
        return result;
    }
    
    /**
     * modGame
     * Function to modify a game in the database
     * @param gToMod
     * @param gID
     * @return int
     */
    public int modGame(String gToMod, int gID)
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(UPDATE_GAME_INFO);
            pstmt.setString(1, gToMod);
            pstmt.setInt(2, gID);
            result = pstmt.executeUpdate();
        }catch(SQLException ex)
        {
            result = -1;
            myLogger.log(Level.INFO, "Exception trying to modify a game with ID->"+gID+": {0}", ex.getMessage());
        }finally{
            try{
                conn.close();
                pstmt.close();
            }catch(SQLException ex)
            {
                myLogger.log(Level.SEVERE, "Exception, could not close all the DB stuff: {0}", ex.getMessage());
            }
        }
        return result;
    }
    
    
    /**
     * createLogger
     * Method to instanciate the logger
     * @param none
     * @return none
     */
    private void createLogger() {
        try {
            URL url = getClass().getResource("GameADOLog.log");
            File logFile = new File(url.getPath());
            FileHandler myFileHandler = new FileHandler(logFile.getAbsolutePath(), (2*1024*1024), 1, true);
            myFileHandler.setFormatter(new SimpleFormatter());
            myLogger = Logger.getLogger("enma.proven.ffinder.entities.persistence.GameADO.log");
            myLogger.addHandler(myFileHandler);
            myLogger.setUseParentHandlers(false);
        } catch (IOException | SecurityException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
