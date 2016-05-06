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
        return aGameList;
    }
}
