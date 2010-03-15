/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.validators;

import com.validation.IValidator;
import com.validation.VMessages;
import com.validation.annotation.ZipCode;
import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jiren
 */
public class ZipCodeValidator extends BasePatternValidator implements IValidator<String,ZipCode> {

    //Format : XXXXX
    private static final String ZIP_PATTERN = "\\d{5}(-\\d{4})?";
    private Pattern pattern = Pattern.compile(ZIP_PATTERN);

    public boolean validate(String zipCode, ZipCode aZipCode, VMessages validationMsges) {
        
        if(!validatePattern(zipCode, pattern, aZipCode.mandatory()))
        {
          validationMsges.addMessage(aZipCode.message());
            return false;
        }

        return true;
    }
}
