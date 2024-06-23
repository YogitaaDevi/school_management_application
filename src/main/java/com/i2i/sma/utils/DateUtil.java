package com.i2i.sma.utils;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;

/**
 * <p>
 * This class deals with validating a date in its correct format and calculating the difference between two dates to find the difference between.
 * </p>
 */
public final class DateUtil {

    private DateUtil() {}

    /**
     * <p>
     * This method calculates difference between the current date and the date provided.
     * </p>
     *
     * @param date a date inorder to calculate the difference.
     * @return the difference between the current date and the entered date in years.
     */
    public static int calculateDifferenceBetweenDates(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        int calculatedDifference = Period.between(date, currentDate).getYears();
        return calculatedDifference;
    }

    /**
     * <p>
     * This method is used to validate whether the parsedDate is valid or not in the specified format.
     * </p>
     *
     * @param date a date that can be of any format.
     * @return true if it is in the correct format. otherwise it returns false.
     */
    public static boolean isValidateDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

