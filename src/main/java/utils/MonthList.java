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
public class MonthList {

    private static final List<String> MONTH_SELECTION = Arrays.asList(new String[]{
        "January", "February", "March", "April", "May", "June", "July", "August", "September","October","November","December"});

    public static List<String> getMonthList() {
        return MONTH_SELECTION;
    }

    public static int getMonthCodeByName(String month) {

        for (int i = 0; i < MONTH_SELECTION.size(); i++) {
            if (month.equalsIgnoreCase(MONTH_SELECTION.get(i))) {
                return i;
            }
        }
        return 0;

    }

    public static String getMonthNameByCode(int code) {
        for (int i = 0; i < MONTH_SELECTION.size(); i++) {
            if (code == i) {
                return MONTH_SELECTION.get(i);
            }
        }
        return null;
    }

}
