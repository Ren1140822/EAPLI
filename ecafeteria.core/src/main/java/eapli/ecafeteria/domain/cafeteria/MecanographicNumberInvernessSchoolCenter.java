/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

/**
 * It provides the mecanographic number validation strategy. It implements the
 * Strategy Pattern.
 *
 * @author Miguel Silva - 1150901
 */
public class MecanographicNumberInvernessSchoolCenter implements MecanographicNumberStrategy {

    /**
     * It provides the information about the validation's requirements.
     *
     * @return It returns the instructions.
     */
    @Override
    public String instructions() {
        return "It needs to have four capitalized letters that describe the employee's abbreviation. It must also have six digits to represent\n"
                + "the student number where the first two indicate the year of enrollment.";
    }

    /**
     * It validates the number. It must contain 4 capital characters. It
     * must also contain six digits.
     *
     * @param number The number to validate.
     * @return It returns "true" if the number is valid or "false" otherwise.
     */
    @Override
    public boolean validate(String number) {
        return number.matches("^[A-Z]{4}[0-9]{6}$");
    }

}
