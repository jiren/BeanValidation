/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.validators;

import com.validation.IValidator;
import com.validation.VMessages;
import com.validation.annotation.Length;

/**
 *
 * @author Jiren
 */
public class LengthValidator implements IValidator<String, Length> {

    public boolean validate(String value, Length annotation, VMessages validationMsges) {

        if (value != null) {
            if (value.length() > annotation.max()) {
                validationMsges.addMessage(annotation.message());
                return false;
            }

        } else {
            validationMsges.addMessage(annotation.message());
            return false;
        }

        return true;
    }
}
