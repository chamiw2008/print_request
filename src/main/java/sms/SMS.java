/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sms;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.mobitel.esms.message.SMSManager;
import lk.mobitel.esms.session.SessionManager;
import lk.mobitel.esms.ws.Alias;
import lk.mobitel.esms.ws.SmsMessage;
import lk.mobitel.esms.ws.User;

/**
 *
 * @author Chaminda
 */
public class SMS {

    public static void main(String[] args) {

        ArrayList<String> telNos = new ArrayList<String>();
        telNos.add("0773303353");
        String msg = "Test Message";
        SMS ms = new SMS();
        try {
            int sendMessage = ms.sendMessage(telNos, msg);
            System.out.println("sent > " + sendMessage);
        } catch (IOException ex) {
            Logger.getLogger(SMS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int sendMessage(ArrayList<String> telNos, String msgIn) throws IOException {

        try {
            Socket ignored = new Socket("localhost", 8080);
            System.out.println("socket :" + ignored);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        //Mobitel Test Account
        //user.setUsername("esmsusr_test6");
        //user.setPassword("Password@2008");
        //Ministry of Health Account
        User user = new User();
        user.setUsername("esmsusr_1sdb");
        user.setPassword("1pt5934");
        SessionManager sm = SessionManager.getInstance();

        System.out.println("Logging in.....");

        if (!sm.isSession()) {
            //System.out.println("Not logged in yet! " + sm.isSession());
            sm.login(user);
            System.out.println("Not logged in yet! " + sm.isSession());
        }

        //System.out.println("Logged in!");
        Alias alias = new Alias();
        alias.setAlias("MinOfHealth");
        SmsMessage msg = new SmsMessage();

        msg.setMessage(msgIn);
        //msg.setMessage("test Message new account");

        msg.setSender(alias);

        for (String s : telNos) {
            System.out.println("telNo= " + s);
            msg.getRecipients().add(s);
        }

        try {
            SMSManager smsMgr = new SMSManager();
            //System.out.println("Sending......");
            int sendMessage = smsMgr.sendMessage(msg);
            System.out.println("Sent!" + sendMessage);
            //AuditTransactions.insertAuditRecord(nic, tel + " | " + msgIn + " | SMS Return : " + ret);
            sm.exit();

            return sendMessage;

        } catch (Exception ex) {
            //AuditTransactions.insertAuditRecord(nic, tel + " | " + msg + " | SMS EXCEPTION");
            ex.printStackTrace();
        }
        return 0;
    }
}
