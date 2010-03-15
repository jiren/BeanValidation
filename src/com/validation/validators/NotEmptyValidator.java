/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.validation.validators;

import com.validation.IValidator;
import com.validation.VMessages;
import com.validation.annotation.NotEmpty;

/**
 *
 * @author Jiren
 */
public class NotEmptyValidator implements IValidator<String,NotEmpty> {

    public boolean validate(String value, NotEmpty annotation, VMessages validationMsges) {

        if(value == null || value.trim().length() == 0)
        {
            validationMsges.addMessage(annotation.message());
            return false;
        }

        return true;

    }

}
