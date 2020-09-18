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
package enterrequests;

import entities.Systemusers;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import reviewletter.Review;

/**
 *
 * @author Data
 */
public final class ViewRequestEntry extends Panel {

    public ViewRequestEntry(String id, final Systemusers entry, final int docid) {
        super(id);

        add(new Link<Void>("ViewLink") {
            @Override
            public void onClick() {
                
                
                PageParameters pageParameters = new PageParameters();
		pageParameters.add("docid", docid);
                setResponsePage(Review.class,pageParameters);

            }
        });

    }
}
