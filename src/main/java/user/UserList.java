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
package user;

import db.TransactionsInstitutions;
import db.TransactionsUserData;
import entities.Insts;
import entities.Systemusers;
import java.util.ArrayList;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 *
 * @author Data
 */
public final class UserList extends Panel {

    Model institutionModel;

    int increment;

    public UserList(String id) {
        super(id);

        ArrayList list = TransactionsUserData.getUsers();

        final ArrayList instList = TransactionsInstitutions.getInstitutions();

        increment = 1;

        final ListView dataView = new ListView("institutions", list) {

            public void populateItem(final ListItem item) {

                final Systemusers u = (Systemusers) item.getModelObject();

                item.add(new Label("no", (increment) + ""));

                String selectedInstName = "";

                for (Object object : instList) {

                    Insts h = new Insts();
                    h = (Insts) object;

                    if (h.getId() == u.getInst()) {
                        selectedInstName = h.getInstitution();
                    }
                }

                item.add(new Label("name", u.getName()));
                item.add(new Label("desig", u.getDesig()));
                item.add(new Label("inst", selectedInstName));
                item.add(new Label("tel1", u.getMobile1()));
                item.add(new Label("tel2", u.getMobile2()));
                item.add(new Label("email1", u.getEmailOff()));
                item.add(new Label("email2", u.getEmailPersonal()));
             

                if (u.getAdmin()) {
                    Label label = new Label("admin", "Yes");
                    label.add(new AttributeAppender("style", new Model("color"), "red"));
                    item.add(label);
                } else {
                    Label label = new Label("admin", "No");
                    label.add(new AttributeAppender("style", new Model("bgcolor"), "red"));
                    item.add(label);

                }

                item.add(new Label("un", u.getUn()));

                //System.out.println("pw : " + u.getPw());
                if (u.getPw().equalsIgnoreCase("12341234")) {
                    item.add(new Label("default", "Yes"));
                } else {
                    item.add(new Label("default", "No"));
                }

                item.add(new ResetUserPW("reset", u));
                item.add(new DeleteEntryUser("inactivate", u));

                increment++;
            }
        ;
        }

            ;

            add(dataView);
    }
}
