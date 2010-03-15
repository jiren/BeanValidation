/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.validators;

import com.validation.IValidator;
import com.validation.VMessages;
import com.validation.annotation.MaxLength;

/**
 *
 * @author Jiren
 */
public class MaxLengthValidator implements IValidator<String,MaxLength> {

    public boolean  validate(String value, MaxLength maxLength, VMessages validationMsg) {
             
        if (value.trim().length() > maxLength.Value()) {
            validationMsg.addMessage(maxLength.Message());
            return false;
        }

        return true;
    }
}
