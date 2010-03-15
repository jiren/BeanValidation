/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * This bean contain Field getter method and field Annotations.
 * @author Jiren
 */
public class AnnoationMethod {
    
    private Method method;
    private Annotation[] annotation;

    public AnnoationMethod( Method method, Annotation[] annotations) {
        this.method = method;
        this.annotation = annotations;
    }
    
    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Annotation[] getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation[] annotation) {
        this.annotation = annotation;
    }

}
