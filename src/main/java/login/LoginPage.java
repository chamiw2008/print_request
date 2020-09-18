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
package login;

import db.AuditTransactions;
import db.TransactionsInstitutions;
import db.TransactionsUserData;
import enterrequests.AddRequests;
import entities.Systemusers;
import home.UserLogoutPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ContextRelativeResource;
import lk.gov.health.chaminda.BasePage;
import lk.gov.health.chaminda.MenuItemEnum;
import lk.gov.health.chaminda.MySession;
import enterrequests.SearchRecords;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class LoginPage extends BasePage {

    Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();

    Boolean isOviyamRequest = false;

    public LoginPage(final PageParameters parameters) {

        //if page parameters available login and forward
        System.out.println("Page parameter ptID : " + parameters.get("ptID"));
        if (!parameters.get("ptID").isNull()) {
            isOviyamRequest = true;
            info("Please login to initiate print request for " + parameters.get("ptName")
                    + " | " + parameters.get("description"));
        }

        ContextRelativeResource govLogo = new ContextRelativeResource("img/gov_logo.png");
        add(new Image("gov-logo", govLogo));

        ContextRelativeResource mailLogo = new ContextRelativeResource("img/sent.gif");
        add(new Image("systemlogo", mailLogo));

        // Add a FeedbackPanel for displaying our messages
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        Label alreadyLogged = new Label("message", "");

        if (currentUser == null) {

            alreadyLogged = new Label("message", "Currently not logged in.");
            add(alreadyLogged);

        } else {

            // If already logged in and is a print request
            // Forward to AddRequest Page with parameters
            if (isOviyamRequest) {
                System.out.println("Is a Oviyam request!");
                System.out.println("Page parameter ptID : " + parameters.get("ptID"));
                setResponsePage(AddRequests.class, parameters);

            }

            alreadyLogged = new Label("message", "Currently logged: " + currentUser.getName() + ", unit: "
                    + TransactionsInstitutions.institutionFromID(currentUser.getInst())
                    + ". \nPlease logout first to login as a different user.");
            add(alreadyLogged);
        }

        UserLogoutPanel logoutPanel = new UserLogoutPanel("logout");
        add(logoutPanel);

        final TextField un = new TextField("un", Model.of(""));
        un.setRequired(true);

        final PasswordTextField pw = new PasswordTextField("pw", Model.of(""));
        pw.setRequired(true);

        Form form = new Form("f") {

            protected void onSubmit() {

                String initialPassword = utils.MD5.md5Convert("12341234");

                Systemusers loginUser = TransactionsUserData.getLoginUser(un.getModelObject().toString(), pw.getModelObject().toString());

                if (loginUser.getId() == null) {

                    //setResponsePage(LoginPage.class);
                    //System.out.println("LoginUser is null");
                    info("Invalid username or password ! try again.");

                } else {

                    ((MySession) Session.get()).setCurrentUser(loginUser);

                    System.out.println("LoginUser populated");

                    if (loginUser.getPw().equals(initialPassword)) {

                        //Current password is default
                        setResponsePage(changeuserpassword.ChangePassword.class);

                    } else {

                        if (isOviyamRequest) {
                            System.out.println("Is a Oviyam request!");
                            System.out.println("Page parameter ptID : " + parameters.get("ptID"));
                            setResponsePage(AddRequests.class, parameters);
                        } else {
                            setResponsePage(SearchRecords.class);
                        }
                        AuditTransactions.insertAuditRecordX(loginUser.getUn(), "User Logged in. ", ((MySession) Session.get()).getComputerID());
                    }
                }
            }
        };

        add(form);
        //form.setMultiPart(true);

        form.add(un);
        form.add(pw);

        if (currentUser == null) {

            form.setVisible(true);
            alreadyLogged.setVisible(false);
            logoutPanel.setVisible(false);

        } else {

            form.setVisible(false);
            alreadyLogged.setVisible(true);
            logoutPanel.setVisible(true);
        }

    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.PRODUCTS;
    }

}
