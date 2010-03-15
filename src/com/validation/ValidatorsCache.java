/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Valdaitors registration and Cache.
 * Bean Fields methods and validation annotation cache.
 * @author Jiren
 */
public final class ValidatorsCache {

    /**
     * Getter method prefix for all primitive and non-premitive datatype except
     * 'boolean' primitive data type
     */
    private static String GETTER_METHOD_PREFIX = "get";
    /**
     * Getter method prefix for 'boolean' datatype.
     */
    private static String GETTER_METHOD_PREFIX_B = "is";

    /**
     * Map of Class name to  fileds annotations and their reflected getter method array.
     */
    private static HashMap<String, AnnoationMethod[]> classMethodsMap = new HashMap<String, AnnoationMethod[]>();

    /**
     * Annotation to Custome validator instance map.
     */
    private static HashMap<Class<? extends Annotation>, IValidator> validatorsMap = new HashMap<Class<? extends Annotation>, IValidator>();

    private ValidatorsCache() {
    }

    /**
     * This method register validator.
     * - Get @AValidation annotation from the validation annotation.
     * - create instance of Validator object and add it to validators map.
     * @param annotation
     */
    private static void registerValidator(Annotation annotation) {


        Class vAnnotationClass = annotation.annotationType();
        try {
            AValidation aValidation = (AValidation) vAnnotationClass.getAnnotation(AValidation.class);
           
            validatorsMap.put(vAnnotationClass, aValidation.validator().newInstance());

            Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, "Registered Validator : " +annotation.toString());

        } catch (InstantiationException ex) {
            Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BeanValidator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Add class fileds 'get'(Getter) Methods and field annotation in Map.
     * - This method check annotation is annotated by 'AValidation' annotation.
     *   Using this check at run time checking the annotation is for validation
     *   or not.
     * - This method add field into cache only if it's getter method has public
     *   modifier.
     * - This method check that valiator already exist in validators map. If not
     *   exist in it than register that validator.So no need to register validator
     *   seperately it loaded on demand basis.
     * @param Class
     */
    private static void addClass(Class klass) {

        try {

            Field[] fields = klass.getDeclaredFields();
            List<AnnoationMethod> aMethodList = new ArrayList<AnnoationMethod>();

            for (int i = 0; i < fields.length; i++) {

                Annotation[] annotations = getAValidates(fields[i]);

                if (annotations.length != 0) {

                    for (int j = 0; j < annotations.length; j++) {
                        if (!validatorsMap.containsKey(annotations[j].annotationType())) {
                            registerValidator(annotations[j]);
                        }
                    }



                    try {

                        Method method = klass.getDeclaredMethod(getGetMethod(fields[i]));

                        if (Modifier.isPublic(method.getModifiers())) {
                            aMethodList.add(new AnnoationMethod(method, annotations));
                        } else {
                            Logger.getLogger(ValidatorsCache.class.getName()).log(Level.WARNING, fields[i] + " field getter method has not public modifier");
                        }

                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(ValidatorsCache.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

            if (!aMethodList.isEmpty()) {

                //Convert list into array and store in to map.
                classMethodsMap.put(klass.getName(), aMethodList.toArray(new AnnoationMethod[aMethodList.size()]));
            }
        } catch (Exception ex) {
            Logger.getLogger(ValidatorsCache.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Get Class fields annotation and getter method object array.
     * - If Class is not present into cache than it add it else it return from
     *   the cache.
     * @param object
     * @return
     */
    public static AnnoationMethod[] getFields(Object object) {

        String className = object.getClass().getName();
        if (!classMethodsMap.containsKey(className)) {
            addClass(object.getClass());
        }
        return classMethodsMap.get(className);
    }

    /**
     * Get field Get Method name.
     * @param fieldName
     * @return
     */
    private static String getGetMethod(String fieldName) {
        return GETTER_METHOD_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * Get field Get Method name.
     * @param fieldName
     * @return
     */
    private static String getGetMethod(Field field) {

        String prefix = null;
        String fieldName = field.getName();

        if (field.getType() != boolean.class) {
            prefix = GETTER_METHOD_PREFIX;
        } else {
            prefix = GETTER_METHOD_PREFIX_B;
        }
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * Check the annotation is AValidation type.
     * @param annotation
     * @return
     */
    public static boolean isAValidation(Class<? extends Annotation> annotation) {
        if (annotation.isAnnotationPresent(AValidation.class)) {
            return true;
        }
        return false;
    }

    /**
     * Get Annotation with AValidation.
     * @param field
     * @return
     */
    private static Annotation[] getAValidates(Field field) {

        Annotation[] tempAnnotations = field.getDeclaredAnnotations();
        ArrayList<Annotation> list = new ArrayList<Annotation>();

        for (int i = 0; i < tempAnnotations.length; i++) {

            Annotation annotation = tempAnnotations[i];
            if (annotation.annotationType().isAnnotationPresent(AValidation.class)) {
                list.add(annotation);
            }
        }

        return list.toArray(new Annotation[list.size()]);
    }

    /**
     * Get Validator from the map.
     * @param vAnnotationClass
     * @return
     */
    public static IValidator getValidator(Annotation vAnnotation) {
        return validatorsMap.get(vAnnotation.annotationType());
    }
}
