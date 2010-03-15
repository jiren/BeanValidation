/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.annotation;

import com.validation.AValidation;
import com.validation.validators.LengthValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Jiren
 */
@AValidation(validator = LengthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Length {

    int max();

    String message();
}
