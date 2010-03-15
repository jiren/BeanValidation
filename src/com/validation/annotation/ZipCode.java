/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.annotation;

import com.validation.AValidation;
import com.validation.validators.EmailValidator;
import com.validation.validators.ZipCodeValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Jiren
 */
@AValidation(validator = ZipCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ZipCode {

    String value();

    boolean mandatory() default true;

    String message();
}
