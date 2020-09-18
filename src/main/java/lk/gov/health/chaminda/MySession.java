/*
 * Copyright 2014 Tomasz Dziurko Bloh.
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

import entities.Systemusers;
import javax.servlet.http.Cookie;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import utils.CookieService;
import utils.SandBox;

/**
 *
 * @author Data
 */
public class MySession extends WebSession {

    private Systemusers currentUser;
    private String ComputerID;
    private int paymonthID;



    public MySession(Request request) {
        super(request);

        currentUser = new Systemusers();
        currentUser = null;

        //Load the cookie service
        CookieService cs = new CookieService();
        //check if cookie present
        Cookie loadCookie = cs.loadCookie(request, "hrims-id");

        if (loadCookie == null) {
            ComputerID = SandBox.randomString(20);
            cs.saveCookie(null, "hrims-id", ComputerID);
            System.out.println("New cookie saved");

        } else {
            ComputerID = loadCookie.getValue();
            System.out.println("Old cookie retreived");
        }

        System.out.println("Cookie : " + ComputerID);

    }

    /**
     * @return the currentUser
     */
    public Systemusers getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(Systemusers currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return the ComputerID
     */
    public String getComputerID() {
        return ComputerID;
    }


}
