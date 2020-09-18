package db;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Chaminda
 */
import entities.Insts;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionsInstitutions implements Serializable {

    public TransactionsInstitutions() {
        super();
    }

    public static String institutionFromID(int id) {

        ArrayList instList = TransactionsInstitutions.getInstitutions();
        for (Object object : instList) {

            Insts h = new Insts();
            h = (Insts) object;

            if (h.getId() == id) {
                return h.getInstitution();
            }
        }

        return null;
    }

    public static int IDFromInstitution(String inst) {

        int id = 0;

        ArrayList instList = TransactionsInstitutions.getInstitutions();
        for (Object object : instList) {

            Insts h = new Insts();
            h = (Insts) object;

            //System.out.println(h.getInstitution() + " | " + inst);
            if (h.getInstitution().equalsIgnoreCase(inst)) {
                id = h.getId();
            }
        }

        return id;
    }

    public static void deleteInstitutionRecord(int id) {

        try {
            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            System.out.println("Deleting .... " + id);

            String insertTableSQL = "DELETE FROM `insts` WHERE `id`= ?;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            dbConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(TransactionsInstitutions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

    }

    public static void insertInstitutionRecord(Insts h) {

        try {
            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            String insertTableSQL = "INSERT INTO `insts` (`id`, `institution`, `parentint`) VALUES (NULL, ?, ?);";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, h.getInstitution());
            preparedStatement.setInt(2, h.getParentint());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            dbConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(TransactionsInstitutions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList getInstitutionsByUserUnit(int parentint) {

        ArrayList list = new ArrayList();

        ResultSet rs = null;

        DatabaseConn conn = new DatabaseConn();
        Connection dbConnection = conn.getConnection();

        try {

            String insertTableSQL = "SELECT * FROM `insts` WHERE `parentint`= ? ORDER BY `institution` ASC;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, parentint);

            // execute insert SQL stetement
            rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst()) {
                //System.out.println("No data");
            } else {
                //System.out.println("Data received");

                while (rs.next()) {

                    Insts h = new Insts();

                    h.setId(rs.getInt("id"));
                    h.setInstitution(rs.getString("institution"));
                    h.setParentint(rs.getInt("parentint"));

                    //System.out.println("inst/unit " + rs.getString("institution"));
                    list.add(h);

                    //System.out.println("Resultset item : " + h.getInstitution());
                }

            }

            dbConnection.close();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {

            return list;
        }

    }

    public static ArrayList getInstitutions() {

        ArrayList list = new ArrayList();

        DatabaseConn conn = new DatabaseConn();
        Connection dbConn = conn.getConnection();

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = dbConn.createStatement();

            if (stmt.execute("SELECT * FROM `insts` ORDER BY `institution` ASC;")) {
                rs = stmt.getResultSet();

            } else {
                System.err.println("select failed");
            }
            while (rs.next()) {

                Insts h = new Insts();

                h.setId(rs.getInt("id"));
                h.setInstitution(rs.getString("institution"));
                h.setParentint(rs.getInt("parentint"));

                list.add(h);

                //System.out.println("Resultset item : " + h.getInstitution());
            }

            dbConn.close();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    /* ignore */

                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    /* ignore */

                }
                stmt = null;
            }
        }
        return list;
    }

    public static Insts getInstitutionByID(int id) {

        Insts h = new Insts();

        try {
            DatabaseConn conn = new DatabaseConn();
            Connection dbConn = conn.getConnection();

            String insertTableSQL = "SELECT * FROM `insts` WHERE `id`= ?;";

            PreparedStatement preparedStatement = dbConn.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);

            // execute insert SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst()) {
                //System.out.println("No data");
            } else {
                //System.out.println("Data received");

                while (rs.next()) {

                    h.setId(rs.getInt("id"));
                    h.setInstitution(rs.getString("institution"));
                    h.setParentint(rs.getInt("parentint"));

                }

            }
            
            dbConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsInstitutions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return h;

    }
}
