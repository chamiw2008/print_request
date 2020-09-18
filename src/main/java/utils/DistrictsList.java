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
public class DistrictsList {

    private static final List<String> DISTRICT_SELECTION = Arrays.asList(new String[]{
        "Ampara", "Anuradhapura", "Badulla", "Batticaloa", "Colombo", "Galle", "Gampaha", "Hambantota", "Jaffna",
        "Kalutara", "Kalmunai", "Kandy", "Kegalle", "Kilinochchi", "Kurunegala", "Mannar", "Matale", "Matara", "Moneralgala",
        "Mullaitivu", "Nuwara Eliya", "Polonnaruwa", "Puttalam", "Ratnapura", "Trincomalee", "Vavuniya"});

    public static List<String> getDistrictList() {
        return DISTRICT_SELECTION;
    }

    public static int getDistrictCodeByName(String province) {

        for (int i = 0; i < DISTRICT_SELECTION.size(); i++) {

            System.out.println("District code" + " | " + province + " | " + DISTRICT_SELECTION.get(i));
            if (province.equalsIgnoreCase(DISTRICT_SELECTION.get(i))) {
                System.out.println("District code (return value) | " + i);
                return i;
            }
        }
        return 0;

    }

    public static String getDistrictNameByCode(int code) {
        for (int i = 0; i < DISTRICT_SELECTION.size(); i++) {
            if (code == i) {
                System.out.println(DISTRICT_SELECTION.get(i));
                return DISTRICT_SELECTION.get(i);
            }
        }
        return null;
    }

}
