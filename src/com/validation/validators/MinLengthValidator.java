/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.validators;

import com.validation.annotation.*;
import com.validation.IValidator;
import com.validation.VMessages;
import java.lang.annotation.Annotation;

/**
 *
 * @author Jiren
 */
public class MinLengthValidator implements IValidator<String, MinLength> {

    public boolean validate(String value, MinLength aMinLength, VMessages validationMsg) {

        if (value != null) {
            if (value.length() < aMinLength.value()) {
                validationMsg.addMessage(aMinLength.message());
                return false;
            }
        }
        else{
            validationMsg.addMessage(aMinLength.message());
            return false;
        }

        return true;
    }
}
