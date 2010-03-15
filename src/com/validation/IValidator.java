/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.validation;

import java.lang.annotation.Annotation;

/**
 *
 * @author Jiren
 */
public interface IValidator<T,A extends Annotation> {

    public boolean  validate(T value, A annotation, VMessages validationMsges);
               
}
