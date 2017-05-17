/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

/**
 * It provides the interface for the mecanographic number validation strategy.
 * It implements the Strategy Pattern.
 *
 * @author Meireles
 */
public interface MecanographicNumberStrategy {

    /**
     * It provides the information about the validation's requirements.
     *
     * @return It returns the instructions.
     */
    String instructions();

    /**
     * It validates the number.
     *
     * @param number The number to validate.
     * @return It returns "true" if the number is valid or "false" otherwise.
     */
    boolean validate(String number);

}
