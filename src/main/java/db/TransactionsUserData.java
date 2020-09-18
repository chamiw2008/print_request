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

import entities.Systemusers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Data
 */
public class TransactionsUserData {

    public static Systemusers getLoginUser(String un, String pwUnEncrypted) {

        String pw = utils.MD5.md5Convert(pwUnEncrypted);        
        System.out.println(pw);

        Systemusers u = new Systemusers();

        DatabaseConn conn = new DatabaseConn();
        Connection dbConnection = conn.getConnection();

        try {

            String insertTableSQL = "SELECT *  FROM `systemusers` WHERE `un` = ? AND `pw` = ?;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, un);
            preparedStatement.setString(2, pw);

            // execute insert SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                System.out.println("Data received");

                while (rs.next()) {

                    u.setInst(rs.getInt("inst"));
                    u.setName(rs.getString("name"));
                    u.setDesig(rs.getString("desig"));
                    u.setUserType(rs.getString("usertype"));
                    u.setMobile1(rs.getString("mobile1"));
                    u.setMobile2(rs.getString("mobile2"));
                    u.setEmailOff(rs.getString("email_off"));
                    u.setEmailPersonal(rs.getString("email_personal"));
                    u.setUn(rs.getString("un"));
                    u.setPw(rs.getString("pw"));
                    u.setAdmin(rs.getBoolean("admin"));
                    u.setId(rs.getInt("id"));

                }
            }

            dbConnection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {

            return u;
        }
    }

    public static void changePassword(int id, String pwUnEncrypted) {

        String pw = utils.MD5.md5Convert(pwUnEncrypted); 

        System.out.println("changing password from - " + id + " |> " + pw);

        try {
            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            String insertTableSQL = "UPDATE `systemusers` SET `pw`= ? WHERE `id`= ?;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, pw);
            preparedStatement.setInt(2, id);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsInstitutions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void resetPassword(Integer id) {

        String pw = utils.MD5.md5Convert("12341234"); 

        try {
            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            String insertTableSQL = "UPDATE `systemusers` SET `pw`= ? WHERE `id`= ?;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, pw);
            preparedStatement.setInt(2, id);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            dbConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(TransactionsInstitutions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertUserRecord(Systemusers u) {

//INSERT INTO `sportsmedicine`.`users` (`id`, `name`, `tel`, `email`, `un`, `pw`, `admin`, `inst`) VALUES (NULL, 'Chaminda Weerabaddana', '0112722822', 'chamiw2008@gmail.com', 'chami', 'chami', '1', '13');
        String pw = utils.MD5.md5Convert("12341234"); 

        try {
            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            String insertTableSQL = "INSERT INTO `systemusers` (`id`, `name`, `Desig`, `UserType`, `mobile2`, `mobile1`, `email_off`, `email_personal`, `un`, `pw`, `admin`, `inst`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, u.getName());
            preparedStatement.setString(2, u.getDesig());
            preparedStatement.setString(3, u.getUserType());
            preparedStatement.setString(4, u.getMobile2());
            preparedStatement.setString(5, u.getMobile1());
            preparedStatement.setString(6, u.getEmailOff());
            preparedStatement.setString(7, u.getEmailPersonal());
            preparedStatement.setString(8, u.getUn());
            preparedStatement.setString(9, pw);
            preparedStatement.setBoolean(10, u.getAdmin());
            preparedStatement.setInt(11, u.getInst());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            dbConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(TransactionsInstitutions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList getUsers() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList list = new ArrayList();

        DatabaseConn conn = new DatabaseConn();
        Connection dbConnection = conn.getConnection();

        Statement stmt = null;
        ResultSet rs = null;

        try {

            stmt = dbConnection.createStatement();

            if (stmt.execute("SELECT * FROM `systemusers` ORDER BY `id` DESC;")) {
                rs = stmt.getResultSet();

            } else {
                System.err.println("select failed");
            }
            while (rs.next()) {

                Systemusers u = new Systemusers();

                u.setInst(rs.getInt("inst"));
                u.setName(rs.getString("name"));
                u.setDesig(rs.getString("desig"));
                u.setUserType(rs.getString("usertype"));
                u.setMobile1(rs.getString("mobile1"));
                u.setMobile2(rs.getString("mobile2"));
                u.setEmailOff(rs.getString("email_off"));
                u.setEmailPersonal(rs.getString("email_personal"));
                u.setUn(rs.getString("un"));
                u.setPw(rs.getString("pw"));
                u.setAdmin(rs.getBoolean("admin"));
                u.setId(rs.getInt("id"));

                // System.out.println("The admin flag : " + rs.getBoolean("admin"));
                if (rs.getBoolean("admin") == true) {
                    u.setAdmin(true);
                } else {
                    u.setAdmin(false);
                }

                list.add(u);

            }

            dbConnection.close();
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

    public static ArrayList getUsersByInstitution(int inst) {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            ArrayList list = new ArrayList();

            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            String sQL = "SELECT * FROM `systemusers` WHERE `inst`= ? ORDER BY `id` DESC;";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sQL);
            preparedStatement.setInt(1, inst);

            // execute insert SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                System.out.println("Data received");
                while (rs.next()) {

                    Systemusers u = new Systemusers();

                    u.setInst(rs.getInt("inst"));
                    u.setName(rs.getString("name"));
                    u.setDesig(rs.getString("desig"));
                    u.setUserType(rs.getString("usertype"));
                    u.setMobile1(rs.getString("mobile1"));
                    u.setMobile2(rs.getString("mobile2"));
                    u.setEmailOff(rs.getString("email_off"));
                    u.setEmailPersonal(rs.getString("email_personal"));
                    u.setUn(rs.getString("un"));
                    u.setPw(rs.getString("pw"));
                    u.setAdmin(rs.getBoolean("admin"));
                    u.setId(rs.getInt("id"));

                    // System.out.println("The admin flag : " + rs.getBoolean("admin"));
                    if (rs.getBoolean("admin") == true) {
                        u.setAdmin(true);
                    } else {
                        u.setAdmin(false);
                    }

                    list.add(u);

                }

                return list;

            }

            dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsUserData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void deleteUserRecord(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            String insertTableSQL = "DELETE FROM `systemusers` WHERE `id`= ?;";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsInstitutions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Systemusers loginUserByUserName(String un) {
        Systemusers u = new Systemusers();

        DatabaseConn conn = new DatabaseConn();
        Connection dbConnection = conn.getConnection();

        try {

            String insertTableSQL = "SELECT *  FROM `systemusers` WHERE `un` = ?";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, un);

            // execute insert SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                System.out.println("Data received");

                while (rs.next()) {

                    u.setInst(rs.getInt("inst"));
                    u.setName(rs.getString("name"));
                    u.setDesig(rs.getString("desig"));
                    u.setUserType(rs.getString("usertype"));
                    u.setMobile1(rs.getString("mobile1"));
                    u.setMobile2(rs.getString("mobile2"));
                    u.setEmailOff(rs.getString("email_off"));
                    u.setEmailPersonal(rs.getString("email_personal"));
                    u.setUn(rs.getString("un"));
                    u.setPw(rs.getString("pw"));
                    u.setAdmin(rs.getBoolean("admin"));
                    u.setId(rs.getInt("id"));

                }
            }

            dbConnection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {

            return u;
        }

    }

    public static Systemusers loginUserByUsersID(int userid) {
        try {

            DatabaseConn conn = new DatabaseConn();
            Connection dbConnection = conn.getConnection();

            String sQL = "SELECT * FROM `systemusers` WHERE `id`= ? ORDER BY `id` DESC;";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sQL);
            preparedStatement.setInt(1, userid);

            // execute insert SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                System.out.println("Data received");
                while (rs.next()) {

                    Systemusers u = new Systemusers();

                    u.setInst(rs.getInt("inst"));
                    u.setName(rs.getString("name"));
                    u.setDesig(rs.getString("desig"));
                    u.setUserType(rs.getString("usertype"));
                    u.setMobile1(rs.getString("mobile1"));
                    u.setMobile2(rs.getString("mobile2"));
                    u.setEmailOff(rs.getString("email_off"));
                    u.setEmailPersonal(rs.getString("email_personal"));
                    u.setUn(rs.getString("un"));
                    u.setPw(rs.getString("pw"));
                    u.setAdmin(rs.getBoolean("admin"));
                    u.setId(rs.getInt("id"));

                    System.out.println("The admin flag : " + rs.getBoolean("admin"));

                    if (rs.getBoolean("admin") == true) {
                        u.setAdmin(true);
                    } else {
                        u.setAdmin(false);
                    }

                    return u;

                }

            }
            dbConnection.close();
            return null;

        } catch (SQLException ex) {
            Logger.getLogger(TransactionsUserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
