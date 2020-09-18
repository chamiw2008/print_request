/*
 * Copyright 2014 Ministry of Health, Sri Lanka.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package db;

import entities.Audits;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;

/**
 *
 * @author HP
 */
public class AuditTransactions {

    static String sqlInsert = "INSERT INTO `audits` (`userid`, `ipaddress`, `useragent`, `computerid`, `timestamp`, `activity`) VALUES ( ?, ?, ?, ?, CURRENT_TIMESTAMP, ?);";

    public static void insertAuditRecordX(String un, String remark, String computerID) {

        // Get the information
        // ip Address
        WebRequest req = (WebRequest) RequestCycle.get().getRequest();
        HttpServletRequest httpReq = (HttpServletRequest) req.getContainerRequest();
        String clientAddress = httpReq.getRemoteHost();

        //get user  agent ie:browser
        String userAgent = WebSession.get().getClientInfo().getUserAgent();

        //Computer id
        //String computerID = ((MySession) Session.get()).getComputerID();
        if (computerID == null) {
            computerID = "Not set yet !";
        }

        try {

            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, un);
            preparedStatement.setString(2, clientAddress);
            preparedStatement.setString(3, userAgent);
            preparedStatement.setString(4, computerID);
            if (remark.length() > 199) {
                preparedStatement.setString(5, remark.substring(199));
            } else {
                preparedStatement.setString(5, remark);
            }

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            dbConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static List getAllAudits() {

        ArrayList<Audits> all = new ArrayList<Audits>();

        DatabaseConn conn = new DatabaseConn();
        Connection dbConnection = conn.getConnection();

        try {

            String allSQL = "SELECT *  FROM `audits` ORDER BY `timestamp` DESC;";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(allSQL);

            // execute insert SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                System.out.println("Data received");

                while (rs.next()) {

                    Audits a = new Audits();

                    a.setUserid(rs.getString("userid"));
                    a.setIpaddress(rs.getString("ipaddress"));
                    a.setUseragent(rs.getString("useragent"));
                    a.setComputerid(rs.getString("computerid"));

                    Timestamp ts = rs.getTimestamp("timestamp");
                    ts.setMinutes(ts.getMinutes() + 690);
                    a.setTimestamp(ts);

                    a.setActivity(rs.getString("activity"));

                    all.add(a);

                }
            }
            dbConnection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {

            return all;
        }
    }

    public static List getAllAudits(String searchString) {

        System.out.println("Searching for ....... " + searchString);

        ArrayList<Audits> all = new ArrayList<Audits>();

        DatabaseConn conn = new DatabaseConn();
        Connection dbConnection = conn.getConnection();

        try {

            String allSQL = "SELECT *  FROM `audits` WHERE LOWER(CONCAT(IFNULL(`userid`, ' '),' ',IFNULL(`ipaddress`, ' '),' ',IFNULL(`computerid`, ' '),' ',IFNULL(`activity`, ' '))) LIKE LOWER( ? )ORDER BY `timestamp` DESC;";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(allSQL);
            preparedStatement.setString(1, "%" + searchString + "%");

            // execute insert SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                System.out.println("Data received");

                while (rs.next()) {

                    Audits a = new Audits();

                    a.setUserid(rs.getString("userid"));
                    a.setIpaddress(rs.getString("ipaddress"));
                    a.setUseragent(rs.getString("useragent"));
                    a.setComputerid(rs.getString("computerid"));
                    a.setTimestamp((rs.getTimestamp("timestamp")));
                    a.setActivity(rs.getString("activity"));

                    all.add(a);

                }
            }

            dbConnection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {

            return all;
        }
    }

}
