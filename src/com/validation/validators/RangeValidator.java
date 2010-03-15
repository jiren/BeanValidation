/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.validators;

import com.validation.IValidator;
import com.validation.VMessages;
import com.validation.annotation.Range;
import java.lang.annotation.Annotation;
import java.math.BigInteger;
import sun.awt.image.PixelConverter.Bgrx;

/**
 *
 * @author Jiren
 */
public class RangeValidator implements IValidator<Number, Range> {

    public boolean validate(Number number, Range annotation, VMessages validationMsges) {


        if(number == null)
        {
           validationMsges.addMessage("Null or " + annotation.message() );
            return false;
        }

        if (number instanceof Integer) {
            if (number.intValue() < annotation.start() || number.intValue() > annotation.end()) {
                validationMsges.addMessage(annotation.message());
                return false;
            }

        } else if (number instanceof Float) {
            if (number.floatValue() < annotation.start() || number.floatValue() > annotation.end()) {
                validationMsges.addMessage(annotation.message());
                return false;
            }
        } else {
            if (number.doubleValue() < annotation.start() || number.doubleValue() > annotation.end()) {
                validationMsges.addMessage(annotation.message());
                return false;
            }
        }

        return true;
    }
}
