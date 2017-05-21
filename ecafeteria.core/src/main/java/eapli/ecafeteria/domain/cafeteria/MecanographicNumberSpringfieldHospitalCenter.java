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
 * @author Meireles
 */
public class MecanographicNumberSpringfieldHospitalCenter implements MecanographicNumberStrategy {

    /**
     * It provides the information about the validation's requirements.
     *
     * @return It returns the instructions.
     */
    @Override
    public String instructions() {
        return "It needs to have two capitalized letters. The first one describes the user type (F - Funcionário or A - Aluno)\n"
                + "and the second the organic unit type (U - Urgência, E - Consultas Externas, F - Faculdade).\n"
                + "It must also have four digits.";
    }

    /**
     * It validates the number. It must contain two characters. It
     * must also contain four digits.
     *
     * @param number The number to validate.
     * @return It returns "true" if the number is valid or "false" otherwise.
     */
    @Override
    public boolean validate(String number) {
        return number.matches("^[FA][UEF][0-9]{4}$");
    }

}
