package home;

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
import db.AuditTransactions;
import entities.Audits;
import entities.Systemusers;
import errorpages.NeedAdminPrivilege;
import errorpages.UseOviyam;
import java.util.List;
import login.LoginPage;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import lk.gov.health.chaminda.BasePage;
import lk.gov.health.chaminda.MenuItemEnum;
import lk.gov.health.chaminda.MySession;

public class AuditTrail extends BasePage {

    int increment;
    String searchString = " ";

    public AuditTrail(final PageParameters parameters) {

        Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();

        if (currentUser == null) {
            throw new RestartResponseException(LoginPage.class);
        }

       

        if (!currentUser.getAdmin()) {
            setResponsePage(NeedAdminPrivilege.class);
        }

        if (!parameters.get("search").isNull()) {
            searchString = parameters.get("search").toString();
        }
        parameters.clearNamed();

        // Add a FeedbackPanel for displaying our messages
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        List allAudits = AuditTransactions.getAllAudits(searchString);

        increment = 1;

        final PageableListView dataView = new PageableListView("audit", allAudits, 100) {

            public void populateItem(final ListItem item) {

                final Audits i = (Audits) item.getModelObject();

                item.add(new Label("no", (increment) + ""));
                item.add(new Label("userid", i.getUserid()));
                item.add(new Label("ip", i.getIpaddress()));

                if (i.getUseragent().length() > 40) {
                    item.add(new Label("agent", i.getUseragent().substring(1, 40)));
                } else {
                    item.add(new Label("agent", i.getUseragent()));
                }

                item.add(new Label("computer", i.getComputerid()));
                item.add(new Label("time", i.getTimestamp().toLocaleString()));
                item.add(new Label("activity", i.getActivity().replaceAll("%20", " ")));
                increment++;

            }
        ;
        };

         add(new PagingNavigator("navigator", dataView));

        add(dataView);

        // Add a form with an onSubmit implementation that sets a message
        final TextField<String> search = new TextField<String>("search", Model.of(""));

        Form<?> form = new Form<Void>("userForm") {

            @Override
            protected void onSubmit() {

                final String searchString = search.getModelObject();
                search.setModelObject(" ");
                System.out.println("Submitting : " + searchString);

                PageParameters pageParameters = new PageParameters();
                if (searchString == null) {
                    pageParameters.add("search", " ");
                    setResponsePage(AuditTrail.class, pageParameters);
                } else {
                    pageParameters.add("search", searchString);
                    setResponsePage(AuditTrail.class, pageParameters);
                }
            }

        };

        add(form);
        form.add(search);

    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.ABOUT_US;
    }

}
