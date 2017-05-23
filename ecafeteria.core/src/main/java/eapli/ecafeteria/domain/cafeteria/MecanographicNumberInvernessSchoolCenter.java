/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

import eapli.util.DateTime;
import eapli.util.Strings;

/**
 * It provides the mecanographic number validation strategy. It implements the
 * Strategy Pattern.
 *
 * @author Miguel Silva - 1150901
 */
public class MecanographicNumberInvernessSchoolCenter implements MecanographicNumberStrategy {

    /**
     * The year from which the admission year is counted.
     */
    private static final int STARTING_YEAR = 2000;

    /**
     * It provides the information about the validation's requirements.
     *
     * @return It returns the instructions.
     */
    @Override
    public String instructions() {
        return "It must have four capital letters for an employee or six numbers (whose the first two matches the admission year) for a student.";
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
        if(Strings.isNullOrWhiteSpace(number)){
            return false;
        }
        return isEmployee(number) || isStudent(number);
    }

    /**
     * It checks if the number matches an employee mecanographic number.
     * 
     * @param number The mecanographic number to validate. It must have four capital letters.
     * @return It returns "true" if it matches an employee mecanographic number or "false" otherwise.
     */
    private boolean isEmployee(String number) {
        return number.matches("^[A-Z]{4}$");
    }

    /**
     * It checks if the number matches a student mecanographic number.
     * 
     * @param number The mecanographic number to validate. It must have six digits whose the first two represent the admission year.
     * @return It returns "true" if it matches a student mecanographic number or "false" otherwise.
     */
    private boolean isStudent(String number) {
        if(number.matches("^[0-9]{6}$")){
            int admissionYear = Integer.parseInt(number.substring(0, 2));
            if(DateTime.currentYear()-STARTING_YEAR>=admissionYear){
                return true;
            }
        }
        return false;
    }

}
