/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.validation.validators;

import com.validation.IValidator;
import com.validation.VMessages;
import com.validation.annotation.NotNull;

/**
 *
 * @author Jiren
 */
public class NotNullValidator implements IValidator<Object,NotNull> {

    public boolean validate(Object value, NotNull annotation, VMessages validationMsges) {

        if(value == null)
        {
            validationMsges.addMessage(annotation.message());
            return false;
        }

        return true;
    }

}
