package com.i2i.sma.utils;

import java.util.regex.Matcher;

/**
 * <p>
 * This class deals with validating the datatype of the data.
 * </p>
 */

public final class DataValidationUtil {

    private DataValidationUtil() {}

    /**
     * <p>
     * This method validates whether the given string contains only alphabets or not.
     * </p>
     *
     * @param str A string that is to be validated.
     *            Whether it may be alphabets or alphanumeric or numbers.
     * @return true if it has only alphabets. Else return false.
     */
    public static boolean validateString(String str) {
        return str.matches("^[a-zA-Z]*$");
    }
}
    
     