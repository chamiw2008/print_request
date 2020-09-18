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
package enterrequests;

import db.TransactionsInstitutions;
import db.TransactionsRequests;
import entities.Requests;
import entities.Systemusers;
import java.util.ArrayList;
import org.apache.wicket.Session;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import lk.gov.health.chaminda.MySession;
import utils.DateFormating;

/**
 *
 * @author Data
 */
public final class ListRequests extends Panel {

    Model institutionModel;

    int increment;

    Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();

    public ListRequests(String id, Boolean routine, Boolean urgent,
            Boolean pending, Boolean completed) {
        super(id);

        ArrayList list = (ArrayList) TransactionsRequests.getRequestsSearchedList(routine, urgent, pending, completed);

        increment = 1;

        final PageableListView dataView = new PageableListView("institutions", list, 150) {

            public void populateItem(final ListItem item) {

                final Requests s = (Requests) item.getModelObject();

                //System.out.println(s.getId() + " " + s.getInst() + " " + s.getSetname() + " " + s.getActive());
                item.add(new Label("no", (increment) + "."));

                item.add(new Label("status", s.getCurrntStatus()) {
                    @Override
                    protected void onComponentTag(final ComponentTag tag) {
                        super.onComponentTag(tag);
                        if (s.getCurrntStatus() == null) {
                            tag.put("style", "background-color:#ff5c33;");

                        }
                        if (s.getCurrntStatus().equalsIgnoreCase("Completed")) {
                            tag.put("style", "background-color:#80ff80;");
                        }

                        if (s.getCurrntStatus().equalsIgnoreCase("Pending"))  {
                            tag.put("style", "background-color:#ffff4d;");
                        }
                        
                         if (s.getCurrntStatus().equalsIgnoreCase("Unable to complete"))  {
                            tag.put("style", "background-color:#F08080;");
                        }

                    }
                });

                //item.add(new Label("id", s.getId()));
                item.add(new ViewRequestEntry("view", currentUser, s.getId()));

                item.add(new Label("ptid", s.getPtid()));
                item.add(new Label("name", s.getPtname()));
                item.add(new Label("desc", s.getDescription()));
                item.add(new Label("studydate", utils.DateFormating.formatDateAndTime(s.getPtstudydate())));

                //System.err.println("Institution ID : " + s.getId());
                item.add(new Label("unit", TransactionsInstitutions.institutionFromID(s.getUnit())));
                item.add(new Label("clinician", s.getSender()));
                item.add(new Label("priority", s.getPriority()));
                item.add(new Label("reqdate", DateFormating.formatDateAndTime(s.getTimeStamps())));
                item.add(new Label("remarks", s.getRemarks()));

                item.add(new Label("statusdate", DateFormating.formatDateAndTime(s.getStatusdt())));
                item.add(new Label("statusremarks", s.getStatusremarks()));

                increment++;
            }
        ;
        };

        add(new PagingNavigator("navigator", dataView));
        add(dataView);
    }
}
