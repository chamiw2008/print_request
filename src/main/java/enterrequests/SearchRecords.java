package enterrequests;

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
import entities.Systemusers;
import errorpages.UseOviyam;
import login.LoginPage;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import lk.gov.health.chaminda.BasePage;
import lk.gov.health.chaminda.MenuItemEnum;
import lk.gov.health.chaminda.MySession;

public class SearchRecords extends BasePage {

    int increment;
    String searchString = "";

    private boolean checkboxRoutine; // 
    private boolean checkboxUrgent; //
    private boolean checkboxPending;
    private boolean checkboxCompleted;

    public SearchRecords(final PageParameters parameters) {

        Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();

        if (currentUser == null) {
            throw new RestartResponseException(LoginPage.class);
        }

        //Initialize the checkbox parameters
        if (parameters.get("routine").isNull()) {
            checkboxRoutine = true;
        }
        //System.out.println("routine X:" + parameters.get("routine").isNull() + checkboxRoutine);

        if (parameters.get("urgent").isNull()) {
            checkboxUrgent = true;
        }

        if (parameters.get("pending").isNull()) {
            checkboxPending = true;
        }

        if (parameters.get("completed").isNull()) {
            checkboxCompleted = false;
        }

        //Handle the parameters when available     
        if (!parameters.get("routine").isNull()) {
            if (parameters.get("routine").toString().equalsIgnoreCase("false")) {
                checkboxRoutine = false;
            } else {
                checkboxRoutine = true;
            }
        }

        if (!parameters.get("urgent").isNull()) {
            if (parameters.get("urgent").toString().equalsIgnoreCase("false")) {
                checkboxUrgent = false;
            } else {
                checkboxUrgent = true;
            }
        }

        if (!parameters.get("pending").isNull()) {
            if (parameters.get("pending").toString().equalsIgnoreCase("false")) {
                checkboxPending = false;
            } else {
                checkboxPending = true;
            }
        }

        if (!parameters.get("completed").isNull()) {
            if (parameters.get("completed").toString().equalsIgnoreCase("false")) {
                checkboxCompleted = false;
            } else {
                checkboxCompleted = true;
            }
        }

        final CheckBox chkr = new CheckBox("chkr",
                new PropertyModel<Boolean>(this, "checkboxRoutine"));

        final CheckBox chku = new CheckBox("chku",
                new PropertyModel<Boolean>(this, "checkboxUrgent"));

        final CheckBox chkP = new CheckBox("pending",
                new PropertyModel<Boolean>(this, "checkboxPending"));

        final CheckBox chkC = new CheckBox("completed",
                new PropertyModel<Boolean>(this, "checkboxCompleted"));

        // Add a FeedbackPanel for displaying our messages
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        //System.out.println("rendering : " + searchString + " " + checkboxRoutine + checkboxUrgent + checkboxImmediate);
        ListRequests ai = new ListRequests("panel", checkboxRoutine, checkboxUrgent, checkboxPending, checkboxCompleted);
        add(ai);

        parameters.clearNamed();


        Form<?> form = new Form<Void>("userForm") {

            @Override
            protected void onSubmit() {

                PageParameters pageParameters = new PageParameters();

         
                if (!checkboxRoutine) {
                    pageParameters.add("routine", "false");
                }
                if (!checkboxUrgent) {
                    pageParameters.add("urgent", "false");
                }
 
                if (!checkboxPending) {
                    pageParameters.add("pending", "false");
                }

                if (!checkboxCompleted) {
                    pageParameters.add("completed", "false");
                }else{
                    pageParameters.add("completed", "true");
                }

                setResponsePage(SearchRecords.class, pageParameters);

                //System.out.println("routine Y:" + pageParameters.get("routine").isNull() + checkboxRoutine);
                //System.out.println("Submitting : " + searchString + " " + checkboxRoutine + checkboxUrgent + checkboxImmediate);
            }

        };

        add(form);
     
        form.add(chkr);
        form.add(chku);
        form.add(chkP);
        form.add(chkC);
     

    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.ABOUT_US;
    }

}
