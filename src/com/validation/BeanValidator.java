/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bean Validator main class.
 * @author Jiren
 */
public class BeanValidator {

    private static final Object[] EMPTY_CLASS_ARRAY = new Object[0];

//  private static HashMap<Class<? extends Annotation>, IValidator> validatorsMap = new HashMap<Class<? extends Annotation>, IValidator>();
//
//    /**
//     * Register Validator Annotation and related Validator Class
//     * @param annotation
//     */
//    public static void registerValidator(Class<? extends Annotation> annotation) {
//
//        if (!ValidatorsCache.isAValidation(annotation)) {
//            throw new IllegalArgumentException(annotation.toString() + " has not valid Validator Annotation() ");
//        }
//
//        if (!validatorsMap.containsKey(annotation)) {
//
//            AValidation iAnnotation = (AValidation) annotation.getAnnotation(AValidation.class);
//            try {
//                IValidator validate = (IValidator) iAnnotation.validator().newInstance();
//                validatorsMap.put(annotation, validate);
//                return;
//            } catch (InstantiationException ex) {
//                Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        throw new IllegalArgumentException(annotation.toString() + " is Already register.");
//    }



    /**
     * Validate object fields.
     * If some field has multiple validation annotation. Which ever first
     * annoation validation beark remaining annotation are not going to
     * validate.
     * @param obj
     * @return
     */
    public static VMessages validate(Object obj) {
        VMessages validationMsg = null;


        AnnoationMethod[] annotationMethods = ValidatorsCache.getFields(obj);

        validationMsg = new VMessages();

        for (AnnoationMethod annotationMethod : annotationMethods) {

            Annotation[] annotations = annotationMethod.getAnnotation();

            Object valueObj = null;
            try {
                valueObj = annotationMethod.getMethod().invoke(obj, EMPTY_CLASS_ARRAY);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, null, ex);

            }

            for (Annotation annotation : annotations) {

                IValidator iValidate = ValidatorsCache.getValidator(annotation);

                if (iValidate != null) {
                    try {

                        if (!iValidate.validate(valueObj, annotation, validationMsg)) {
                            break;
                        }


                    } catch (Exception ex) {
                        Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, "Validator not registered for : " + annotation);
                }

            }
        }

        return validationMsg;
    }
}
