/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Paulo Gandra Sousa
 */
public final class Math {

    /**
     * determines if a number is odd.
     *
     * @param number the number to be tested
     * @return
     */
    public static boolean isOdd(final int number) {
        return number % 2 != 0;
    }

    /**
     * determines if a number is even.
     *
     * @param number the number to be tested
     * @return
     */
    public static boolean isEven(final int number) {
        return number % 2 == 0;
    }

    /**
     * checks whether an int is prime or not.
     * http://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/
     *
     * @param number
     * @return
     */
    public static boolean isPrime(final int number) {
        if (isEven(number)) {
            return false;
        }
        // if not, then just check the odds
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Permite efectuar a conversão de um valor para outra escala.
     *
     * @param oldMin - exemplo 0
     * @param oldMax - exemplo 100
     * @param newMin - exemplo 0
     * @param newMax - exemplo 10
     * @param oldValue - exemplo 50
     * @return retorna o novo valor aplicando uma conversão linear - exemplo 5
     */
    public static float simpleLinearConversion(final float oldMin,
            final float oldMax,
            final float newMin,
            final float newMax,
            final float oldValue) {
        float new_value = ((oldValue - oldMin) / (oldMax - oldMin))
                * (newMax - newMin) + newMin;
        return new_value;
    }

    /**
     * Permite efectuar a conversão de um valor para outra escala.
     *
     * @param oldMin - exemplo 0
     * @param oldMax - exemplo 100
     * @param newMin - exemplo 0
     * @param newMax - exemplo 10
     * @param oldValue - exemplo 50
     * @return retorna o novo valor aplicando uma conversão linear - exemplo 5
     */
    public static BigDecimal simpleLinearConversion(final BigDecimal oldMin,
            final BigDecimal oldMax,
            final BigDecimal newMin,
            final BigDecimal newMax,
            final BigDecimal oldValue) {
        BigDecimal a = oldValue.subtract(oldMin);
        BigDecimal b = oldMax.subtract(oldMin);
        BigDecimal c = a.divide(b, 1, RoundingMode.HALF_UP);
        BigDecimal d = newMax.subtract(newMin);
        BigDecimal new_value = c.multiply(d).add(newMin);
        return new_value;
    }

    public static String format(final BigDecimal amount) {
        // TODO use number formatter
        return amount.toString();
    }

    /**
     * returns a representation of a number as Roman numeral.
     *
     * @param number
     * @return
     */
    public static String formatAsRomanNumeral(final int number) {
        RomanNumeral roman = new RomanNumeral(number);
        return roman.toString();
    }

    private Math() {
        // to make sure this is an utility class
    }
}
