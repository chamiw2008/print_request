/*
 * Copyright 2018 Ministry of Health, Sri Lanka.
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
package lk.gov.health.chaminda;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author User
 */
public class StaticPara {

    //Ministry Server
    private static String database;
    private static String databaseUser;
    private static String databasePW;
    private static String priorityOptions;
    private static String reasonForOrderingOptions;
    private static String statusOptions;

    public StaticPara() {

        loadPropertiesData();

    }

    //public static void main(String[] args) {
    public void loadPropertiesData() {

        Properties prop = new Properties();
        InputStream input = null;
        String filename = "config.properties";
        try {

            // new FileOutputStream("config.properties");
            // set the properties value
            //prop.setProperty("database", "localhost"); //database=printrequest
            //prop.setProperty("dbuser", "mkyong");
            //prop.setProperty("dbpassword", "password");
            // save properties to project root folder
            //prop.store(output, null);

            /*            dbpassword=pq#123
            database=printrequest
            dbuser=pqadmin
            priorityOptions=Routine,Urgent
            reasonOptions=Transfer the patient, Significant finding, Teaching purposes, Medicolegal issue
            statusOptions=Completed, Unable to complete*/
            
            
            // input = new FileInputStream("config.properties");
            input = WicketApplication.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            //System.out.println(prop.getProperty("database"));
            //System.out.println(prop.getProperty("dbuser"));
            //System.out.println(prop.getProperty("dbpassword"));

            setDatabase(prop.getProperty("database"));
            setDatabaseUser(prop.getProperty("dbuser"));
            setDatabasePW(prop.getProperty("dbpassword"));
            setPriorityOptions(prop.getProperty("priorityOptions"));//"Routine,Urgent";
            setReasonForOrderingOptions(prop.getProperty("reasonOptions"));//"Transfer the patient, Significant finding, Teaching purposes, Medicolegal issue";
            setStatusOptions(prop.getProperty("statusOptions"));//"Completed, Unable to complete";

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * @return the databasePW
     */
    public String getDatabasePW() {
        return databasePW;
    }

    /**
     * @return the databaseUser
     */
    public String getDatabaseUser() {
        return databaseUser;
    }

    /**
     * @param aDatabaseUser the databaseUser to set
     */
    public void setDatabaseUser(String aDatabaseUser) {
        databaseUser = aDatabaseUser;
    }

    /**
     * @param aDatabasePW the databasePW to set
     */
    public void setDatabasePW(String aDatabasePW) {
        databasePW = aDatabasePW;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * @return the priorityOptions
     */
    public String getPriorityOptions() {
        return priorityOptions;
    }

    /**
     * @return the reasonForOrderingOptions
     */
    public String getReasonForOrderingOptions() {
        return reasonForOrderingOptions;
    }

    /**
     * @return the statusOptions
     */
    public String getStatusOptions() {
        return statusOptions;
    }

    /**
     * @param aDatabase the database to set
     */
    public void setDatabase(String aDatabase) {
        database = aDatabase;
    }

    /**
     * @param aPriorityOptions the priorityOptions to set
     */
    public void setPriorityOptions(String aPriorityOptions) {
        priorityOptions = aPriorityOptions;
    }

    /**
     * @param aReasonForOrderingOptions the reasonForOrderingOptions to set
     */
    public void setReasonForOrderingOptions(String aReasonForOrderingOptions) {
        reasonForOrderingOptions = aReasonForOrderingOptions;
    }

    /**
     * @param aStatusOptions the statusOptions to set
     */
    public void setStatusOptions(String aStatusOptions) {
        statusOptions = aStatusOptions;
    }

}
