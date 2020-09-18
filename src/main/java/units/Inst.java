package units;

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
import db.TransactionsInstitutions;
import entities.Insts;
import entities.Systemusers;
import errorpages.NeedAdminPrivilege;
import errorpages.UseOviyam;
import java.sql.Timestamp;
import java.util.ArrayList;
import login.LoginPage;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import lk.gov.health.chaminda.BasePage;
import lk.gov.health.chaminda.MenuItemEnum;
import lk.gov.health.chaminda.MySession;

public class Inst extends BasePage {

    final ArrayList list = TransactionsInstitutions.getInstitutions();

    private String selectedParent = "";

    public Inst() {

        ArrayList instList = new ArrayList();
        instList.add("None");

        final Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();

        if (currentUser == null) {
            throw new RestartResponseException(LoginPage.class);
        }

        if (!currentUser.getAdmin()) {
            setResponsePage(NeedAdminPrivilege.class);
        }

        for (int i = 0; i < list.size(); i++) {

            Insts h = (Insts) list.get(i);
            //System.out.println(i + " " + h.getInstitution());
            instList.add(h.getInstitution());
        }

        // Add a FeedbackPanel for displaying our messages
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        ListInstitution ai = new ListInstitution("panel");
        add(ai);

        // Add institution display field
        // Will be based on the credentials of the user logged in
        final TextField<String> institution = new TextField<String>("institutions", Model.of(""));
        institution.setRequired(true);

        DropDownChoice<String> parent = new DropDownChoice<String>("parent", new PropertyModel<String>(this, "selectedParent"), instList);
        parent.setRequired(true);

        // Add a form with an onSubmit implementation that sets a message
        Form<?> form = new Form<Void>("form") {

            @Override
            protected void onSubmit() {

                final String institutionValue = institution.getModelObject();
                //info(institutionValue);

                Insts h = new Insts();

                h.setInstitution(institutionValue);
                h.setParentint(TransactionsInstitutions.IDFromInstitution(selectedParent));

                //System.out.println(h.getInstitution() + " | " + h.getParentint());
                TransactionsInstitutions.insertInstitutionRecord(h);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                AuditTransactions.insertAuditRecordX(currentUser.getUn(), "User record entered  " + timestamp, ((MySession) Session.get()).getComputerID());

                institution.setModelValue(new String[]{""});

                setResponsePage(Inst.class);
            }

        };

        add(form);
        form.add(institution);
        form.add(parent);

    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.NONE;
    }

}
