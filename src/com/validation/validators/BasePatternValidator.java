/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.validators;

import java.util.regex.Pattern;

/**
 *
 * @author Jiren
 */
public class BasePatternValidator {

    /**
     * validate Patten.
     * Mandatory = false : if it is null or empty return true. else check for pattern.
     * Mandatory = true  : if it is null return false else check for pattern
     * @param input
     * @param pattern
     * @param mandatory
     * @param message
     * @return
     */
    public boolean validatePattern(String input, Pattern pattern, boolean mandatory) {

        if (!mandatory) {
            if (input == null) {
                return true;
            } else if (input.trim().isEmpty()) {
                return true;
            }
        }
        else if(mandatory)
        {
            if(input == null)
            {
                return false;
            }
        }


        return pattern.matcher(input).matches();
        //validationMsges.addMessage(message);
            
        
    }
}
