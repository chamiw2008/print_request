/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reviewletter;

import db.TransactionsInstitutions;
import db.TransactionsRequests;
import entities.Requests;
import entities.Systemusers;
import errorpages.UseOviyam;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import lk.gov.health.chaminda.BasePage;
import lk.gov.health.chaminda.MenuItemEnum;
import lk.gov.health.chaminda.MySession;
import enterrequests.SearchRecords;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.string.StringValue;
import lk.gov.health.chaminda.StaticPara;
import utils.DateFormating;

/**
 *
 * @author Data
 */
public class Review extends BasePage {

    int DocID = 0;
    String docIDString = "0";
    String hash1 = "";
    String hash2 = "";
    String decryptUserID = "";
    private String selectedStatus = "";

    public Review(final PageParameters params) {

        System.out.println("Running the Review.class submit code");
        Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();
        if (currentUser == null) {
            throw new RestartResponseException(login.LoginPage.class);
        }

        Requests i = new Requests();

        StringValue docIDparam = getRequest().getRequestParameters().getParameterValue("docid");
        DocID = Integer.parseInt(docIDparam.toString());

        //Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");

        StaticPara sp = new StaticPara();
        String[] statusOptionArray = sp.getStatusOptions().split(",");
        List<String> STATUS_SELECTION = Arrays.asList(statusOptionArray);

        final DropDownChoice<String> update = new DropDownChoice<String>("update", new PropertyModel<String>(this, "selectedStatus"), STATUS_SELECTION);
        update.setRequired(true);
        update.setNullValid(true);

        final TextField<String> updateRemarks = new TextField<String>("updateremarks", Model.of(""));
        updateRemarks.setRequired(false);

        Form form = new Form("f") {

            @Override
            protected void onSubmit() {

                System.out.println("Submitted status: " + selectedStatus);
                //System.out.println("Submitted remarks: " + updateRemarks.getModelObject().toString());

                String toString = "";
                if (updateRemarks.getModelObject() != null) {
                    toString = updateRemarks.getModelObject();
                }
                if (!selectedStatus.equalsIgnoreCase("Completed")) {

                    if (toString.isEmpty()) {
                        info("Please fill the remarks column with the reasons if unable to complete print request.");
                    } else {
                        TransactionsRequests.updateRequestStatus(DocID, selectedStatus, toString);
                        setResponsePage(SearchRecords.class);
                    }

                } else {
                    TransactionsRequests.updateRequestStatus(DocID, selectedStatus, toString);
                    setResponsePage(SearchRecords.class);
                }

            }
        };

        add(form);

        if (!currentUser.getAdmin()) {
            form.setEnabled(false);
        }

        form.add(feedbackPanel);

        i = TransactionsRequests.getRequest(DocID);

        form.add(new Label("ptid", i.getPtid()));
        form.add(new Label("name", i.getPtname()));
        form.add(new Label("desc", i.getDescription()));
        form.add(new Label("studydate", utils.DateFormating.formatDateAndTime(i.getPtstudydate())));

        //System.err.println("Institution ID : " + s.getId());
        form.add(new Label("unit", TransactionsInstitutions.institutionFromID(i.getUnit())));
        form.add(new Label("clinician", i.getSender()));
        form.add(new Label("priority", i.getPriority()));
        form.add(new Label("reqdate", DateFormating.formatDateAndTime(i.getTimeStamps())));
        form.add(new Label("remarks", i.getRemarks()));

        form.add(update);
        form.add(updateRemarks);
    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.ABOUT_US;
    }
}
