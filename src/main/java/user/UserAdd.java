package user;

import db.AuditTransactions;
import db.TransactionsInstitutions;
import db.TransactionsUserData;
import entities.Insts;
import entities.Systemusers;
import errorpages.NeedAdminPrivilege;
import errorpages.UseOviyam;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import utils.StringRangeValidator;

public class UserAdd extends BasePage {

    private static final List<String> ADMIN_SELECTION = Arrays.asList(new String[]{
        "User", "Admin"});

    private static final List<String> INST_SELECTION = new ArrayList<String>();

    private String selectedInst = "";
    private String selectedType = "";
    private String selectedAdmin = "";

    //Get all the institutions list
    ArrayList instList = TransactionsInstitutions.getInstitutions();

    Systemusers currentUser = ((MySession) Session.get()).getCurrentUser();

    public UserAdd() {

        if (currentUser == null) {
            throw new RestartResponseException(LoginPage.class);
        }

        if (!currentUser.getAdmin()) {
            setResponsePage(NeedAdminPrivilege.class);
        }

        INST_SELECTION.clear();

        for (Object object : instList) {

            Insts h = new Insts();
            h = (Insts) object;

            INST_SELECTION.add(h.getInstitution());

        }

        // Add a FeedbackPanel for displaying our messages
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);

        UserList ai = new UserList("panel");
        add(ai);

        DropDownChoice<String> inst = new DropDownChoice<String>("inst", new PropertyModel<String>(this, "selectedInst"), INST_SELECTION);
        inst.setRequired(true);
        inst.setNullValid(true);

        final TextField<String> name = new TextField<String>("name", Model.of(""));
        name.setRequired(true);

        final TextField<String> desig = new TextField<String>("desig", Model.of(""));
        desig.setRequired(true);
        desig.add(new StringRangeValidator(1, 30));

        final TextField<String> tel1 = new TextField<String>("tel1", Model.of(""));
        //tel1.setRequired(true);
        tel1.add(new StringRangeValidator(1, 10));

        final TextField<String> tel2 = new TextField<String>("tel2", Model.of(""));
        tel2.add(new StringRangeValidator(1, 10));

        final TextField<String> email1 = new TextField<String>("email1", Model.of(""));
        email1.add(new StringRangeValidator(1, 50));
        //email1.setRequired(true);

        final TextField<String> email2 = new TextField<String>("email2", Model.of(""));
        email2.add(new StringRangeValidator(1, 50));

        final TextField<String> un = new TextField<String>("un", Model.of(""));
        un.setRequired(true);
        un.add(new StringRangeValidator(1, 20));

        DropDownChoice<String> admin = new DropDownChoice<String>("uAdmin", new PropertyModel<String>(this, "selectedAdmin"), ADMIN_SELECTION);
        admin.setRequired(true);

        // Add a form with an onSubmit implementation that sets a message
        Form<?> form = new Form<Void>("form") {

            @Override
            protected void onSubmit() {

                Systemusers u = new Systemusers();

                int selectedInstID = 0;

                for (Object object : instList) {

                    Insts h = new Insts();
                    h = (Insts) object;

                    if (h.getInstitution().equalsIgnoreCase(selectedInst)) {
                        selectedInstID = h.getId();
                    }

                    INST_SELECTION.add(h.getInstitution());
                }

                u.setInst(selectedInstID);
                u.setName(name.getModelObject());
                u.setDesig(desig.getModelObject());
                u.setMobile1(tel1.getModelObject());
                u.setMobile2(tel2.getModelObject());
                u.setEmailOff(email1.getModelObject());
                u.setEmailPersonal(email2.getModelObject());
                u.setUn(un.getModelObject());
                //u.setPw("12341234");
                

                u.setUserType(selectedType);

                if (selectedAdmin.equalsIgnoreCase("user")) {
                    u.setAdmin(false);
                } else {
                    u.setAdmin(true);
                }

                TransactionsUserData.insertUserRecord(u);
                //Record is inserted

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                AuditTransactions.insertAuditRecordX(currentUser.getUn(), "User record entered  " + timestamp, ((MySession) Session.get()).getComputerID());

                name.setModelValue(new String[]{""});
                desig.setModelValue(new String[]{""});
                tel1.setModelValue(new String[]{""});
                tel2.setModelValue(new String[]{""});
                email1.setModelValue(new String[]{""});
                email1.setModelValue(new String[]{""});
                un.setModelValue(new String[]{""});
                selectedInst = "";
                selectedAdmin = "";

                setResponsePage(UserAdd.class);

            }

        };

        add(form);
        form.add(inst);
        form.add(name);
        form.add(desig);
        form.add(tel1);
        form.add(tel2);
        form.add(email1);
        form.add(email2);
        form.add(un);
        form.add(admin);
    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.NONE;
    }

}
