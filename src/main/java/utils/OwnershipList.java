/*
 * Copyright 2014 Ministry of Health, Sri Lanka.
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
package utils;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Data
 */
public class OwnershipList {

    private static final List<String> OWNERSHIP_SELECTION = Arrays.asList(new String[]{
        "Line Ministry", "Provincial"});

    public static List<String> getOwnershipList() {
        return OWNERSHIP_SELECTION;
    }

    public static int getOwnershipCodeByName(String province) {

        for (int i = 0; i < OWNERSHIP_SELECTION.size(); i++) {

            System.out.println("Ownership code" + " | " + province + " | " + OWNERSHIP_SELECTION.get(i));

            if (province.equalsIgnoreCase(OWNERSHIP_SELECTION.get(i))) {
                //System.out.println("Ownership code (return value) | " + i);
                return i;
            }
        }
        return 0;

    }

    public static String getOwnershipNameByCode(int code) {
        for (int i = 0; i < OWNERSHIP_SELECTION.size(); i++) {
            if (code == i) {
                return OWNERSHIP_SELECTION.get(i);
            }
        }
        return null;
    }

}
