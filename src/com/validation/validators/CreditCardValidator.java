/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation.validators;

import com.validation.IValidator;
import com.validation.VMessages;
import com.validation.annotation.CreditCard;
import java.util.regex.Pattern;

/**
 *
 * @author Jiren
 */
public class CreditCardValidator extends BasePatternValidator implements IValidator<String, CreditCard> {

    private final static String VISA_PATTERN = "^4[0-9]{12}(?:[0-9]{3})?$";
    private final static String MASTER_PATTERN = "^5[1-5][0-9]{14}$";
    private final static String AMEX_PATTERN = "^3[47][0-9]{13}$";
    private final static String DISCOVER_PATTERN = "^6(?:011|5[0-9]{2})[0-9]{12}";
    private final static String DINERS_PATTERN = "^3(?:0[0-5]|[68][0-9])[0-9]{11}$";
    private final static String JCB_PATTERN = "^(?:2131|1800|35\\d{3})\\d{11}$";
//    private static final String VISA_START = "4";
//    private static final String MASTER_START = "5";
//    private static final String AMEX_START_1 = "34";
//    private static final String AMEX_START_2 = "37";
//    private static final String DISCOVER_START = "6";
//    private static final String DINERS_START = "3";
//    //private static final String JCB_START      ="3";
//    private Pattern patternVisa = Pattern.compile(VISA_PATTERN);
//    private Pattern patternMaster = Pattern.compile(MASTER_PATTERN);
//    private Pattern patternAmex = Pattern.compile(AMEX_PATTERN);
//    private Pattern patternDiscover = Pattern.compile(DISCOVER_PATTERN);
//    private Pattern patternDiners = Pattern.compile(DINERS_PATTERN);
//    private Pattern patternJCB = Pattern.compile(JCB_PATTERN);
    private Pattern pattern = null;
    private StringBuffer patternStr = new StringBuffer();

    public CreditCardValidator() {
        patternStr.append("(" + VISA_PATTERN + ")|(");
        patternStr.append("(" + MASTER_PATTERN + ")|(");
        patternStr.append("(" + AMEX_PATTERN + ")|(");
        patternStr.append("(" + DISCOVER_PATTERN + ")|(");
        patternStr.append("(" + DINERS_PATTERN + ")|(");
        patternStr.append("(" + JCB_PATTERN + ")");
        pattern = Pattern.compile(patternStr.toString());

    }

    public boolean validate(String value, CreditCard annotation, VMessages validationMsges) {

        value = value.replace("[- ]", "");

        if (annotation.luhnchecksum()) {
            if (!checkSum(value)) {
                validationMsges.addMessage(annotation.message());
                return false;
            }
        }

        if(!validatePattern(value, pattern, annotation.mandatory()))
        {
            validationMsges.addMessage(annotation.message());
            return false;
        }

        return true;
    }

    /**
     * Luhn Algo to check card number is valid or not.
     * @param cardNumber
     * @return
     */
    private boolean checkSum(String cardNumber) {

        char[] digitChars = cardNumber.toCharArray();
        Integer[] digits = new Integer[digitChars.length];
        Integer sum = 0;

        for (int i = 0; i < digits.length; i++) {
            digits[i] = Character.getNumericValue(digitChars[i]);
            if (((i + 1) % 2) != 0) {
                int oddnumber = digits[i] * 2;
                if (oddnumber > 9) {
                    oddnumber = oddnumber - 9;
                }

                //   oddnumber = oddnumber > 9? oddnumber-9:oddnumber;

                sum = sum + oddnumber;

            } else {
                sum = sum + digits[i];
            }
        }

        if (((sum) % 10) == 0) {
            return true;
        }
        return false;

    }

//    public static void main(String[] args) {
//
//        CreditCardValidator creditCardValidator = new CreditCardValidator();
//
//        String patternStr = "(" + VISA_PATTERN + ")|(" + MASTER_PATTERN + ")";
//
//        Pattern pattern = Pattern.compile(patternStr);
//
//        String card = "5811111111111111";
//
//        if (pattern.matcher(card).matches()) {
//            System.out.println("Match");
//        } else {
//            System.out.println("not match");
//        }
//
//
//    }
}
