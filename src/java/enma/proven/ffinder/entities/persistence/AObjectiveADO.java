package enma.proven.ffinder.entities.persistence;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import enma.proven.ffinder.entities.AObjective;
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
public class AObjectiveADO {
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
    static final String GET_ALL_OBJECTIVES = "SELECT * FROM `objective`";
    public AObjectiveADO(){
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
     * getAllObjectives
     * Function to return a list with all the objectives
     * @return List<AObjective>
     */
    public List<AObjective> getAllobjectives()
    {
        List<AObjective> aList = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AObjective aO = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(GET_ALL_OBJECTIVES);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                aO = new AObjective(rs.getInt(1), rs.getString(2));
                aList.add(aO);
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace(System.out);
            System.out.println("Error handling the data" + ex.getMessage());
        }finally{
            try{
                conn.close();
                pstmt.close();
                rs.close();
            }catch(SQLException ex)
            {
                System.out.println("Couldn't close al database stuff");
            }
        }
        return aList;
    }
}
