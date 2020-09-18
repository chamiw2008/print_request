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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Data
 */
public class YearList {

    private static int startYear = 2010;

    public static List<String> getYearList() {

        List<String> YEAR_SELECTION = new ArrayList<String>();
        YEAR_SELECTION.clear();

        Date d = new Date();
        int currentYear = d.getYear() + 1900;

        //System.out.println("Current Year : " + currentYear);
        //System.out.println("Start Year : " + startYear);
        for (int i = startYear; i <= currentYear; i++) {
            //System.out.println("i : " + i);
            YEAR_SELECTION.add(i + "");

        }

        //System.out.println("Year : " + YEAR_SELECTION);
        return YEAR_SELECTION;
    }

}
