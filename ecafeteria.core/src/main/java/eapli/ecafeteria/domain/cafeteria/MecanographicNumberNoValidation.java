
package eapli.ecafeteria.domain.cafeteria;

/**
 * It provides the mecanographic number validation strategy.
 * It implements the Strategy Pattern.
 *
 * @author Meireles
 */
public class MecanographicNumberNoValidation implements MecanographicNumberStrategy {

    /**
     * It provides the information about the validation's requirements.
     *
     * @return It returns the instructions.
     */
    @Override
    public String instructions() {
        return "No special requirements.";
    }

    /**
     * It validates the number.
     *
     * @param number The number to validate.
     * @return It always returns "true".
     */
    @Override
    public boolean validate(String number) {
        return true;
    }

}
