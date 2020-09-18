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
package user;

import db.TransactionsUserData;
import entities.Systemusers;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Data
 */
public final class ResetUserPW extends Panel {

    public ResetUserPW(String id, final Systemusers entry) {
        super(id);

        add(new Link<Void>("resetLink") {
            @Override
            public void onClick() {

                TransactionsUserData.resetPassword(entry.getId());
                setResponsePage(UserAdd.class);

            }
        });

    }
}
