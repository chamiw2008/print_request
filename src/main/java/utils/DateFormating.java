
/*
 * Copyright 2018 Ministry of Health, Sri Lanka.
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class DateFormating {

    public static void main(String[] args) {
        //formatDateAndTime(new Date());
        //formatJustDate(new Date());
        stringToDateTime("20/08/2018 02:03");
    }

    public static String formatDateAndTime(Date date) {
        String format = null;
        try {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
            return format;
        } catch (Exception e) {
        }
        //System.out.println("date and time :" + format);
        return format;
    }

    public static String formatJustDate(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        //System.out.println("date " + ":" + format);
        return format;
    }

    public static java.util.Date stringToDateTime(String stringDate) {

        System.out.println("Date as String:" + stringDate);
        //studyDate	20/08/2018 02:03
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        //String dateInString = "20/08/2018 02:03";
        String dateInString = stringDate;

        try {

            Date date = formatter.parse(dateInString);
            System.out.println("As date "+date);
            //String format = formatter.format(date);
            //System.out.println("As formatted date"+format);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

        
        
        
    }
   
    
}
