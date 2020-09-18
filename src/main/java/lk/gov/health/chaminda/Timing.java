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
package lk.gov.health.chaminda;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Data
 */
public class Timing extends Thread {

    volatile boolean flag = true;

    public void run() {
        while (flag) {
            // Do your task
            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            //System.err.println(sdf.format(cal.getTime()));

            try {
                Thread.sleep(1000* 60 * 60);
            } catch (Exception e) {

            }

        }
    }

}
