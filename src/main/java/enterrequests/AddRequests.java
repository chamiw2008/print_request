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
import db.TransactionsInstitutions;
import db.TransactionsRequests;
import entities.Insts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import lk.gov.health.chaminda.BasePage;
import lk.gov.health.chaminda.MenuItemEnum;
import entities.Requests;
import entities.Systemusers;
import errorpages.UseOviyam;
import errorpages.UseOviyam;
import login.LoginPage;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.lang.Generics;
import org.apache.wicket.util.string.StringValue;
import lk.gov.health.chaminda.MySession;
import lk.gov.health.chaminda.StaticPara;

public class AddRequests extends BasePage {

    private String selectedUnit = "";
    private String selectedPriority = "";
    private String selectedReason = "";

    List<String> selected = Generics.newArrayList();

    private Boolean isAdmin = false;

    public AddRequests(final PageParameters parameters) {

        StaticPara sp = new StaticPara();

        String[] priorityOptionArray = sp.getPriorityOptions().split(",");
        List<String> PRIORITY_SELECTION = Arrays.asList(priorityOptionArray);

        String[] reasonForPrintOptionArray = sp.getReasonForOrderingOptions().split(",");
        List<String> REASON_SELECTION = Arrays.asList(reasonForPrintOptionArray);

        //Check if properly initiated via Oviyam
        StringValue patientID = getRequest().getRequestParameters().getParameterValue("ptID");

        if (patientID.isNull()) {
            //System.out.println("Patient ID received."+patientID);
            //info("Print request must be initiated via Oviyam DICOM viewer.");
            throw new RestartResponseException(UseOviyam.class, parameters);
        }

        //Only oviyam initiated requests come here
        Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();
        if (currentUser == null) {
            //System.out.println("Not logged in!");
            throw new RestartResponseException(LoginPage.class, parameters);
        }

        ArrayList<String> filteredlist = new ArrayList<String>();
        ArrayList allinstList = TransactionsInstitutions.getInstitutions();

        if (currentUser.getAdmin()
                == true) {
            for (Object object : allinstList) {
                Insts i = (Insts) object;
                System.out.println(i.getInstitution());
                filteredlist.add(i.getInstitution());
            }
        } else {
            filteredlist.add(TransactionsInstitutions.getInstitutionByID(currentUser.getInst()).getInstitution());
        }

        // Add a FeedbackPanel for displaying our messages
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");

        //Get parameter data
        //StringValue patientID = getRequest().getRequestParameters().getParameterValue("ptID");
        StringValue patientName = getRequest().getRequestParameters().getParameterValue("ptName");
        StringValue studyID = getRequest().getRequestParameters().getParameterValue("studyID");
        StringValue modality = getRequest().getRequestParameters().getParameterValue("modality");
        StringValue description = getRequest().getRequestParameters().getParameterValue("description");
        StringValue studyDate = getRequest().getRequestParameters().getParameterValue("studyDate");

        //check if required data available
        if (patientID.isNull()) {
            info("Print request must be initiated via Oviyam DICOM viewer.");
        } else {

            //populate entity
            final Requests s = new Requests();
            s.setPtid(patientID.toString());
            s.setPtname(patientName.toString());
            s.setStudyid(studyID.toString());
            String tableDescription = "[" + modality.toString() + "] " + description.toString() + " | " + studyID.toString();
            s.setModality(modality.toString());
            s.setDescription(description.toString());
            s.setPtstudydate(utils.DateFormating.stringToDateTime(studyDate.toString()));

            DropDownChoice<String> unit = new DropDownChoice<String>("unit", new PropertyModel<String>(this, "selectedUnit"), filteredlist);
            unit.setRequired(true);

            final TextField<String> clinician = new TextField<String>("clinician", Model.of(""));
            clinician.setRequired(true);

            final DropDownChoice<String> priority = new DropDownChoice<String>("priority", new PropertyModel<String>(this, "selectedPriority"), PRIORITY_SELECTION);
            priority.setRequired(true);

            final DropDownChoice<String> reason = new DropDownChoice<String>("reason", new PropertyModel<String>(this, "selectedReason"), REASON_SELECTION);
            reason.setNullValid(true);
            reason.setRequired(true);

            final TextArea<String> remarks = new TextArea<String>("remarks", Model.of(""));
            //remarks.setRequired(true);

            Form<Void> form = new Form<Void>("form") {

                @Override
                protected void onSubmit() {

                    Requests r = new Requests();

                    //import all passed data to new object
                    r.setPtid(s.getPtid());
                    r.setPtname(s.getPtname());
                    r.setStudyid(s.getStudyid());
                    r.setModality(s.getModality());
                    r.setDescription(s.getDescription());
                    r.setPtstudydate(s.getPtstudydate());
                    r.setUnit(TransactionsInstitutions.IDFromInstitution(selectedUnit));
                    r.setPriority(selectedPriority);
                    r.setSender(clinician.getModelObject().toString());
                    
                    String remarksString = "";
                    if (remarks.getModelObject() == null){
                    
                    }else{
                        remarksString = remarks.getModelObject().toString();
                    }
                            
                    r.setRemarks(" <" + selectedReason + "> " + remarksString);
                    r.setCurrntStatus("Pending");

                    System.out.println("Inserting records !");
                    TransactionsRequests.insertRequestRecord(r);

                    setResponsePage(SearchRecords.class);
                }

            };

            add(form);
            form.add(feedbackPanel);

            form.add(new Label("ptid", s.getPtid()));
            form.add(new Label("name", s.getPtname()));
            form.add(new Label("desc", tableDescription));
            form.add(new Label("studydate", s.getPtstudydate().toString()));

            form.add(unit);
            form.add(clinician);
            form.add(priority);
            form.add(reason);
            form.add(remarks);
        }
    }

    @Override

    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.NONE;
    }

}
