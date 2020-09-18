package lk.gov.health.chaminda;

import com.googlecode.wicket.kendo.ui.resource.KendoAllResourceReference;
import com.googlecode.wicket.kendo.ui.settings.KendoUILibrarySettings;
import contact.ContactPage;
import login.LoginPage;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.RequestCycleSettings;

public class WicketApplication extends WebApplication {

    @Override
    public Class<LoginPage> getHomePage() {      
        return LoginPage.class;
    }

    public Session newSession(Request request, Response response) {
        return new MySession(request);
    }

    @Override
    public void init() {

        //Timing t = new Timing();
        //t.start();
        getRequestCycleSettings().setGatherExtendedBrowserInfo(true);
        getRequestCycleSettings().setRenderStrategy(RequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);

        //mountPage("Evaluate", EvalPage1.class);
        mountPage("contact", ContactPage.class);

        getMarkupSettings().setStripWicketTags(true);
        setExternalHtmlDirIfSystemPropertyIsPresent();

        //KendoUILibrarySettings settings = KendoUILibrarySettings.get();
        ////settings.setJavaScriptReference(new JQueryPluginResourceReference(...)); // if you want to change the js version
        //settings.setCommonStyleSheetReference(new CssResourceReference(WicketApplication.class, "kendo.common.min.css"));
        //settings.setThemeStyleSheetReference(new CssResourceReference(WicketApplication.class, "kendo.custom.min.css"));
        // jQuery //
        // this.getJavaScriptLibrarySettings().setJQueryReference(JQueryMigrateResourceReference.get());
        // Kendo UI //
        
        KendoUILibrarySettings settings = KendoUILibrarySettings.get();
        settings.setJavaScriptReference(KendoAllResourceReference.get()); // kendo.all.min.js

    }

    private void setExternalHtmlDirIfSystemPropertyIsPresent() {
        String externalDir = System.getProperty("wicket.html.dir");
        if (externalDir != null) {
            //getResourceSettings().addResourceFolder(externalDir);
        }
    }
}
