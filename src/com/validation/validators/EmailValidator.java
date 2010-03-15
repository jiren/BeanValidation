/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.validators;

import com.validation.IValidator;
import com.validation.VMessages;
import com.validation.annotation.Email;
import java.util.regex.Pattern;

/**
 *
 * @author Jiren
 */
public class EmailValidator extends BasePatternValidator implements IValidator<String,Email> {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
 
    public boolean validate(String email, Email aEmail, VMessages validationMsges) {
               
         if(!validatePattern(email, pattern, aEmail.mandatory()))
         {
            validationMsges.addMessage(aEmail.message());
            return false;
         }

         return true;


    }
}
