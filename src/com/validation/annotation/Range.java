/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.validation.annotation;

/**
 *
 * @author Jiren
 */

import com.validation.AValidation;
import com.validation.validators.EmailValidator;
import com.validation.validators.RangeValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@AValidation(validator = RangeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
    float start();
    float end();
    String message();

}
