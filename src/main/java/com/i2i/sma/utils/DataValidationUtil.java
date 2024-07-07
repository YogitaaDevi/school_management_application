package com.i2i.sma.utils;

/**
 * <p>
 * This class deals with validating the datatype of the data.
 * </p>
 */

public final class DataValidationUtil {

    private DataValidationUtil() {
    }

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

    /**
     * <p>
     * This method checks whether the given int exist within the range.
     * </p>
     *
     * @param num An int to be checked.
     * @return true if it exists within the range. Else return false.
     */
    public static boolean checkNumberRange(int num) {
        if (num > 0 && num <= 12) {
            return true;
        }
        return false;
    }
}
    
     