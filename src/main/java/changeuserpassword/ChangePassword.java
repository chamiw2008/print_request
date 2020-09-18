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
package changeuserpassword;

import db.TransactionsUserData;
import enterrequests.SearchRecords;
import entities.Systemusers;
import errorpages.UseOviyam;
import login.LoginPage;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import lk.gov.health.chaminda.BasePage;
import lk.gov.health.chaminda.MenuItemEnum;
import lk.gov.health.chaminda.MySession;

public class ChangePassword extends BasePage {

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.PRODUCTS;
    }

    Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();

    public ChangePassword() {

        if (currentUser == null) {
            throw new RestartResponseException(LoginPage.class);
        }

        // Add a FeedbackPanel for displaying our messages
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        Label defalutPW = new Label("message", "You are using the default password '12341234' Please reset it now ! ");
        add(defalutPW);
        defalutPW.setVisible(false);

        if (currentUser.getPw().equalsIgnoreCase("12341234")) {
            defalutPW.setVisible(true);
        }

        Form form;

        final PasswordTextField password = new PasswordTextField("pw", Model.of(""));
        password.setRequired(true);

        final PasswordTextField passwordConfirm = new PasswordTextField("pw1", Model.of(""));
        passwordConfirm.setRequired(true);

        form = new Form("f") {

            protected void onSubmit() {

                if (password.getModelObject().equalsIgnoreCase(passwordConfirm.getModelObject())) {
                    try {

                        //System.out.println(currentUser.getId() + " | " + password.getModelObject().toString());
                        TransactionsUserData.changePassword(currentUser.getId(), password.getModelObject().toString());
                        setResponsePage(SearchRecords.class);

                    } catch (Exception ex) {
                        System.err.println(ex);
                    }
                } else {
                    info("Passwords do not match !");
                }

            }
        };

        form.add(password);
        form.add(passwordConfirm);

        add(form);
    }

}
