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
package utils;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

/**
 *
 * @author Data
 */
public class IntRangeValidator implements IValidator<String> {

    int min = 0;
    int max = 0;

    public IntRangeValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(IValidatable<String> validatable) {

        final String value = validatable.getValue();

        try {
            int intValue = Integer.parseInt(value);
            if (intValue < min || intValue > max) {
                error(validatable, "Value not in the range of " + min + " to " + max + ".");

            }
        }catch (Exception  e){
                error(validatable, "only accepts numbers in the range of " + min + " to " + max + ".");        
        }
    }

    private void error(IValidatable<String> validatable, String errorMsg) {
        ValidationError error = new ValidationError();
        error.setMessage(errorMsg);
        validatable.error(error);
    }

}
