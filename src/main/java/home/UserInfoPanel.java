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
package home;

import db.TransactionsInstitutions;
import entities.Systemusers;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import lk.gov.health.chaminda.MySession;

/**
 *
 * @author Data
 */
public final class UserInfoPanel extends Panel {

    public UserInfoPanel(String id) {
        super(id);

        final Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();

        if (currentUser == null) {
            add(new Label("loggeduser", " Not logged in !"));
            // java code
            add(new Label("userinstitution", ""));
        } else {
            // java code
            add(new Label("loggeduser", "Current User : " + currentUser.getUn() + " - ( " + currentUser.getName() + " )"));
            // java code
            add(new Label("userinstitution", "Current Institution : " + TransactionsInstitutions.institutionFromID(currentUser.getInst())));
        }
    }
}
