/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.validators;

import com.validation.IValidator;
import com.validation.VMessages;
import com.validation.annotation.Phone;
import java.util.regex.Pattern;

/**
 *
 * @author Jiren
 */
public class PhoneValidator extends  BasePatternValidator implements IValidator<String,Phone> {

    private static final String PHONE_PATTERN = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
    private static Pattern pattern = Pattern.compile(PHONE_PATTERN);

    public boolean validate(String phone, Phone aPhone, VMessages validationMsges) {
        
        if(!validatePattern(phone, pattern, aPhone.mandatory()))
        {
            validationMsges.addMessage(aPhone.message());
            return false;
        }


        return true;

    }
}
