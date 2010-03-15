/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.annotation;

import com.validation.AValidation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Jiren
 */
@AValidation(validator = com.validation.validators.MaxLengthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MaxLength {

    int Value();

    String Message();
}
