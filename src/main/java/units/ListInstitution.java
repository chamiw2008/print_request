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
package units;

import db.TransactionsInstitutions;
import entities.Insts;
import java.util.ArrayList;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 *
 * @author Data
 */
public final class ListInstitution extends Panel {

    Model institutionModel;

    int increment;

    public ListInstitution(String id) {
        super(id);

        ArrayList list = TransactionsInstitutions.getInstitutions();

        increment = 1;

        final ListView dataView = new ListView("institutions", list) {

            public void populateItem(final ListItem item) {

                final Insts i = (Insts) item.getModelObject();

                item.add(new Label("no", (increment) + ""));

                item.add(new Label("inst", i.getInstitution()));
                item.add(new Label("parent", TransactionsInstitutions.institutionFromID(i.getParentint())));

                item.add(new DeleteEntryInstitutions("delete", i));

                increment++;

            }
        ;
        };

    add(dataView);

    }
}
