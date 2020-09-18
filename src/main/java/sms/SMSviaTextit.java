/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;

import db.AuditTransactions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.wicket.Session;
import lk.gov.health.chaminda.MySession;

/**
 *
 * @author Chaminda
 */
public class SMSviaTextit {

    public SMSviaTextit() {
    }

    public static boolean sendTextITBiz(String un, String tele, String msg) {

        if (tele.trim().length() != 10) {
            System.out.println("Tel length too long : " + tele);
            return false;
        }

        try {

            String withoutLeadingZero = tele.trim().substring(1);
            String msgNew = msg.trim().replaceAll(" ", "+");

            String url = "http://www.textit.biz/sendmsg?id=94773303353&pw=3754&to=94" + withoutLeadingZero + "&text=" + msgNew + ".";

            System.out.println("The URL = " + url);

            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.err.println(inputLine);

                String entry = "SMS sent : " + tele + "| Response : " + inputLine;
                System.out.println(entry);

                //Audit
                AuditTransactions.insertAuditRecordX(un, msg, ((MySession) Session.get()).getComputerID());

                if (inputLine.contains("OK")) {
                    return true;
                }
            }
            in.close();

        } catch (IOException ex) {
            System.err.println("Exception : " + ex);
        }

        return false;

    }
}
