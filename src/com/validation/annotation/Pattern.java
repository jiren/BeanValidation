/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.validation.annotation;

import com.validation.AValidation;
import com.validation.validators.PatternValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Jiren
 */

@AValidation(validator = PatternValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Pattern {

    String pattern();
    String message();
}
