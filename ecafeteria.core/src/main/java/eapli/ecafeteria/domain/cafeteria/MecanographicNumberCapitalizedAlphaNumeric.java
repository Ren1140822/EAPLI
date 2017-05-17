/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

import eapli.util.Strings;

/**
 * It provides the mecanographic number validation strategy.
 * It implements the Strategy Pattern.
 *
 * @author Meireles
 */
public class MecanographicNumberCapitalizedAlphaNumeric implements MecanographicNumberStrategy {

    /**
     * It provides the information about the validation's requirements.
     *
     * @return It returns the instructions.
     */
    @Override
    public String instructions() {
        return "It can only contain letters and digits. It must have at least one capital letter and one digit.";
    }

    /**
     * It validates the number. It must contain only alphanumeric characters. It must contain at least one capital letter and one digit.
     *
     * @param number The number to validate.
     * @return It returns "true" if the number is valid or "false" otherwise.
     */
    @Override
    public boolean validate(String number) {
        return number.matches("^[a-zA-Z0-9]*$")
                && Strings.containsCapital(number)
                && Strings.containsDigit(number);
    }

}
