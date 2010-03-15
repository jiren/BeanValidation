/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.validators;

import com.validation.IValidator;
import com.validation.VMessages;
import com.validation.annotation.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jiren
 */
public class PatternValidator implements IValidator<String, Pattern> {

    public boolean validate(String value, Pattern annotation, VMessages validationMsges) {

        try {

            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(value);

            if(!pattern.matcher(value).matches())
            {
                 validationMsges.addMessage(annotation.message());
                 return false;
            }

        } catch (Exception ex) {
            Logger.getLogger(PatternValidator.class.getName()).log(Level.SEVERE, null, ex);
        }


        return true;
    }
}
