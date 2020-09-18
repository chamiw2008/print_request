package db;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Chaminda
 */
import entities.Requests;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionsRequests implements Serializable {

    public static Requests getRequest(int id) {

        System.out.println("Retreiving Record ID:" + id);

        DatabaseConn conn = new DatabaseConn();
        Connection dbConnection = conn.getConnection();

        ResultSet rs = null;

        try {

            String sQL = "SELECT * FROM `requests` WHERE `id` = ?;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sQL);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            if (rs != null) {
                //System.err.println("select successful");
            } else {
                System.err.println("select failed");
            }

            while (rs.next()) {

                Requests s = new Requests();

                s.setId(rs.getInt("id"));
                s.setUnit(rs.getInt("Unit"));
                s.setPtname(rs.getString("ptname"));
                s.setPtstudydate(rs.getDate("ptstudydate"));
                s.setStudyid(rs.getString("studyid"));
                s.setPtid(rs.getString("ptid"));

                s.setModality(rs.getString("modality"));
                s.setDescription(rs.getString("description"));

                s.setSender(rs.getString("Sender"));
                s.setPriority(rs.getString("Priority"));
                s.setRemarks(rs.getString("Remarks"));
                s.setTimeStamps(rs.getTimestamp("TimeStamps"));
                s.setCurrntStatus(rs.getString("currntStatus"));

                s.setStatusdt(rs.getDate("statusdt"));
                s.setStatusremarks(rs.getString("statusremarks"));
                return s;
            }

            dbConnection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return null;

    }

    public static List<Requests> getRequests() {

        List<Requests> list = new ArrayList<Requests>();

        DatabaseConn conn = new DatabaseConn();
        Connection dbConnection = conn.getConnection();

        ResultSet rs = null;

        try {

            String sQL = "SELECT * FROM `requests`;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sQL);
            rs = preparedStatement.executeQuery();

            if (rs != null) {
                //System.err.println("select successful");
            } else {
                System.err.println("select failed");
            }

            while (rs.next()) {

                Requests s = new Requests();

                s.setId(rs.getInt("id"));
                s.setUnit(rs.getInt("Unit"));
                s.setPtname(rs.getString("ptname"));
                s.setPtstudydate(rs.getDate("ptstudydate"));
                s.setStudyid(rs.getString("studyid"));
                s.setPtid(rs.getString("ptid"));

                s.setModality(rs.getString("modality"));
                s.setDescription(rs.getString("description"));

                s.setSender(rs.getString("Sender"));
                s.setPriority(rs.getString("Priority"));
                s.setRemarks(rs.getString("Remarks"));
                s.setTimeStamps(rs.getTimestamp("TimeStamps"));
                s.setCurrntStatus(rs.getString("currntStatus"));

                s.setStatusdt(rs.getDate("statusdt"));
                s.setStatusremarks(rs.getString("statusremarks"));

                list.add(s);
            }

            dbConnection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;

    }

    public static void updateRequestStatus(int docid, String status, String statusRemarks) {

        try {
            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            //System.out.println("Updating .... " + docid);
            //String insertTableSQL = "DELETE FROM `letters`.`letters` WHERE `id`= ?;";
            String insertTableSQL = "UPDATE `requests` SET `currntStatus` = ?, `statusdt` = ?, `statusremarks` = ? WHERE `id` = ? ;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, status);

            LocalDate now = java.time.LocalDate.now();
            Date date = Date.valueOf(now);
            preparedStatement.setDate(2, date);

            preparedStatement.setString(3, statusRemarks);
            preparedStatement.setInt(4, docid);

            // execute insert SQL stetement
            int executeUpdate = preparedStatement.executeUpdate();

            System.out.println("Status Updated " + executeUpdate);
            dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsRequests.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public TransactionsRequests() {
        super();
    }

    public static void deleteRequestRecord(int id) {

        try {
            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            System.out.println("Deleting .... " + id);

            String insertTableSQL = "DELETE FROM `requests` WHERE `id`= ?;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            dbConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(TransactionsRequests.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int insertRequestRecord(Requests s) {

        try {
            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            ResultSet rs = null;

            String insertTableSQL = "INSERT INTO `requests` (`Unit`, `Sender` "
                    + ",`Priority`, `Remarks`, `TimeStamps`"
                    + ",`currntStatus` ,`ptname`,`ptstudydate`,`studyid`,`ptid`"
                    + ",`modality`,`description`,`statusdt`,`statusremarks`)"
                    + "VALUES "
                    + "(?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?, ?, ? ,?);";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, s.getUnit());
            preparedStatement.setString(2, s.getSender());
            preparedStatement.setString(3, s.getPriority());
            preparedStatement.setString(4, s.getRemarks());
            preparedStatement.setString(5, s.getCurrntStatus());

            preparedStatement.setString(6, s.getPtname());

            java.util.Date ptstudydate = s.getPtstudydate();
            java.sql.Date sqlPtStudyDate = new java.sql.Date(ptstudydate.getTime());
            preparedStatement.setDate(7, sqlPtStudyDate);

            preparedStatement.setString(8, s.getStudyid());
            preparedStatement.setString(9, s.getPtid());

            preparedStatement.setString(10, s.getModality());
            preparedStatement.setString(11, s.getDescription());

            java.util.Date statusdt = s.getStatusdt();
            java.sql.Date sqlStatusdt = null;
            try {
                sqlStatusdt = new java.sql.Date(statusdt.getTime());
                preparedStatement.setDate(12, sqlStatusdt);
            } catch (Exception e) {
                preparedStatement.setDate(12, null);
            }

            preparedStatement.setString(13, s.getStatusremarks());

            preparedStatement.executeUpdate();

            rs = preparedStatement.getGeneratedKeys();

            //System.out.println("Heeerrr !!!");
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                System.out.println("last inserted id : " + last_inserted_id + " for unit entry :" + s.getUnit());
                return last_inserted_id;
            }

            dbConnection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TransactionsRequests.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public static ArrayList getRequestsSearchedList(Boolean routine, Boolean urgent, Boolean pending, Boolean completed) {

        ArrayList list = new ArrayList();

        DatabaseConn conn = new DatabaseConn();
        Connection dbConnection = conn.getConnection();
        ResultSet rs = null;

        System.out.println("priority : " + "Routine>" + routine + "Urgent>" + urgent + "Pending>"
                + pending + "Completed" + completed);
        //searchString = "MPI";

        try {

            String rst = null;
            if (routine) {
                rst = "Routine";
            }

            String ust = null;
            if (urgent) {
                ust = "Urgent";
            }

            String pend = null;
            if (pending) {
                pend = "Pending";
            }

            String comp = null;
            if (completed) {
                comp = "Completed";
            }

            String unable = "Unable to complete";

            String allSQL = "SELECT *  FROM `requests` "
                    + "WHERE "
                    + "`Priority` IN ( ?, ? ) AND `currntStatus` IN ( ?, ?, ? )"
                    + " ORDER BY `TimeStamps` DESC;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(allSQL);

            preparedStatement.setString(1, rst);
            preparedStatement.setString(2, ust);
            preparedStatement.setString(3, pend);
            preparedStatement.setString(4, comp);
            preparedStatement.setString(5, unable);

            rs = preparedStatement.executeQuery();

            if (rs != null) {
                System.err.println("select successful");
            } else {
                System.err.println("select failed");
            }

            while (rs.next()) {

                Requests s = new Requests();

                s.setId(rs.getInt("id"));
                s.setUnit(rs.getInt("Unit"));
                s.setPtname(rs.getString("ptname"));
                s.setPtstudydate(rs.getDate("ptstudydate"));
                s.setStudyid(rs.getString("studyid"));
                s.setPtid(rs.getString("ptid"));

                s.setModality(rs.getString("modality"));
                s.setDescription(rs.getString("description"));

                s.setSender(rs.getString("Sender"));
                s.setPriority(rs.getString("Priority"));
                s.setRemarks(rs.getString("Remarks"));
                s.setTimeStamps(rs.getTimestamp("TimeStamps"));
                s.setCurrntStatus(rs.getString("currntStatus"));

                s.setStatusdt(rs.getDate("statusdt"));
                s.setStatusremarks(rs.getString("statusremarks"));

                list.add(s);
            }

            dbConnection.close();
            
            

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        System.out.println("Number of objects fetched :"+list.size());
        return list;
    }

}
