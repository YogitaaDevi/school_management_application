package com.i2i.sma.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * <p>
 * This class deals with validating a date in its correct format and calculating the difference between two dates to find the difference between.
 * </p>
 */
public final class DateUtil {

    private DateUtil() {
    }

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
     * This method is used to validate whether the parsedDate is not greater than
     * current day and not lesser than 20 years from today.
     * </p>
     *
     * @param date a date that can be of any format.
     * @return true if it is in the correct format. otherwise it returns false.
     */
    public static boolean isValidateDate(LocalDate date) {
        try {
            if ((!date.isAfter(LocalDate.now())) &&
                    (!date.isBefore(LocalDate.now().minus(20, ChronoUnit.YEARS)))) {
                return true;
            }
            return false;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

