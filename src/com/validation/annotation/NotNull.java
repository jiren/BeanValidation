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
import com.validation.validators.NotNullValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@AValidation(validator = NotNullValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotNull  {

    String message();
}
