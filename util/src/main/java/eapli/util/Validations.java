/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.util;

import java.util.Calendar;

/**
 * An utility class for validations.
 *
 * @author Paulo Gandra Sousa
 */
public final class Validations {

    /**
     * checks whether a String is empty (zero length or all spaces) or null
     *
     * @param text
     * @return
     */
    public static boolean isNullOrEmpty(String text) {
        return (text == null || text.isEmpty());
    }

    /**
     * checks whether a String is empty (zero length or all spaces) or null
     *
     * @param text
     * @return
     */
    public static boolean isNullOrWhiteSpace(final String text) {
        return (text == null || text.trim().isEmpty());
    }

    // TODO should we move this method to the DateTime class?
    public static boolean isSameYear(final Calendar a, final Calendar b) {
        return a.get(Calendar.YEAR) == b.get(Calendar.YEAR);
    }

    // TODO should we move this method to the DateTime class?
    public static boolean isSameMonth(final Calendar a, final Calendar b) {
        return a.get(Calendar.MONTH) == b.get(Calendar.MONTH);
    }

    /**
     * Checks if a number is positive (greater than zero)
     *
     * @param number the number to check
     * @return true if number is positive
     */
    public static boolean isPositiveNumber(final long number) {
        //TODO should we rename the method to isPositive() ?
        return (number > 0);
    }

    private Validations() {
        // to make sure this is an utility class
    }
}
