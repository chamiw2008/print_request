package lk.gov.health.chaminda;

import changeuserpassword.ChangePassword;
import contact.ContactPage;
import home.AuditTrail;
import enterrequests.SearchRecords;
import units.Inst;
import login.LoginPage;
import org.apache.wicket.markup.html.WebPage;
import enterrequests.AddRequests;
import user.UserAdd;

/**
 *
 * @author HP
 */
public abstract class BasePage extends WebPage {

    public BasePage() {

        //ContextRelativeResource crr = new ContextRelativeResource("img/healthpayroll.png");
        //add(new Image("logo", crr));
        
        //add(new Image("logo", "C:\\Users\\Data\\Desktop\\My Projects\\PayrollHR\\healthpayroll.png"));
        add(new TwitterBootstrapNavBarPanel.Builder("navBar", LoginPage.class, "Radiology Print Request Manager", getActiveMenu())
                .withMenuItemAsDropdown(MenuItemEnum.PRODUCTS, UserAdd.class, "Manage Users (Admin)")
                .withMenuItemAsDropdown(MenuItemEnum.PRODUCTS, Inst.class, "Manage Units (Admin)")
                .withMenuItemAsDropdown(MenuItemEnum.ABOUT_US, AddRequests.class, "Add Request")
                .withMenuItemAsDropdown(MenuItemEnum.PRODUCTS, AuditTrail.class, "Audit Trails (Admin)")
                .withMenuItemAsDropdown(MenuItemEnum.ABOUT_US, SearchRecords.class, "List Requests")
                //.withMenuItemAsDropdown(MenuItemEnum.ABOUT_US, Review.class, "View Letter Entry")
                //.withMenuItemAsDropdown(MenuItemEnum.ABOUT_US, Blank.class, "Print Daily Log")
                .withMenuItemAsDropdown(MenuItemEnum.CLIENTS, ChangePassword.class, "Change Password")
                .withMenuItemAsDropdown(MenuItemEnum.CLIENTS, LoginPage.class, "Login/Logout")
                // .withMenuItemAsDropdown(MenuItemEnum.CLIENTS, ChangePassword.class, "Change Password")
                // .withMenuItemAsDropdown(MenuItemEnum.CLIENTS, LoginPage.class, "Login")
                .withMenuItemAsDropdown(MenuItemEnum.CONTACT, ContactPage.class, "Contact Us")
                .build());

    }

    public abstract MenuItemEnum getActiveMenu();

}
