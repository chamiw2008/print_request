package oviyaminterface;

import java.util.Set;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;

public class HomePageX extends WebPage {

    private static final long serialVersionUID = 1L;

    /*    public HomePage(PageParameters params) {
    
    //works when the uRL uses GET method
    //http://localhost:8080/URLSendData/?param1=66116228133328015752528485170529
    
    StringValue pcID = params.get("param1");
    StringValue printerType = params.get("param2");
    StringValue imageData = params.get("param3");
    System.out.println("Data = " + pcID + " | " + printerType + " | " + imageData);
    
    }*/
    public HomePageX(final PageParameters parameters) {
             

        Set<String> parameterNames = getRequest().getRequestParameters().getParameterNames();

        RepeatingView repeating = new RepeatingView("repeating");
        add(repeating);

        for (String parameterName : parameterNames) {

            AbstractItem item = new AbstractItem(repeating.newChildId());
            repeating.add(item);

            String value = getRequest().getRequestParameters().getParameterValue(parameterName).toString("not specified");
            String results = "Parameter : Name-" + parameterName + " value-" + value;
            System.out.println(results);

            item.add(new Label("name", parameterName));
            item.add(new Label("value", value));

        }

    }
}
