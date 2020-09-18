package db;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.gov.health.chaminda.StaticPara;

/**
 *
 * @author Chaminda
 */
public class DatabaseConn {

    private Connection DBConn;

    public Connection getConnection() {

        StaticPara sp = new StaticPara();
        String database = sp.getDatabase();

        try {
            String dbURL = "jdbc:mysql://localhost/" + database;

            String username = sp.getDatabaseUser();
            String password = sp.getDatabasePW();

            //System.out.println("User :" + username + " & password :" + password);
            Class.forName("com.mysql.jdbc.Driver");
            try {

                if (DBConn == null) {
                    DBConn = DriverManager.getConnection(dbURL, username, password);
                    //System.out.println("Database Connection.................Established");
                }

            } catch (SQLException ex) {
                System.out.println("Exception : " + ex);
                Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            System.err.println("Failed to load mysql driver");
            System.err.println(ex);
        }

        return DBConn;

    }

}
