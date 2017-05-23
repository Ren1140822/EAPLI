package eapli.ecafeteria.domain.cafeteria;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Meireles
 */
public class MecanographicNumberSpringfieldHospitalCenterTest {

    @Test
    public void ensureNumbersHaveSixCharacters() {
        MecanographicNumberSpringfieldHospitalCenter instance = new MecanographicNumberSpringfieldHospitalCenter();
        assertTrue(instance.validate("FU1234"));
        assertFalse(instance.validate("FU12345"));
        assertFalse(instance.validate("FU123"));
    }

    @Test
    public void ensureNumbersStartWithUserValidType() {
        MecanographicNumberSpringfieldHospitalCenter instance = new MecanographicNumberSpringfieldHospitalCenter();
        assertTrue(instance.validate("FU1234"));
        assertTrue(instance.validate("AU1234"));
        assertFalse(instance.validate("TU1234"));
    }

    @Test
    public void ensureNumbersSecondCharacterMatchesValidOrganicUnit() {
        MecanographicNumberSpringfieldHospitalCenter instance = new MecanographicNumberSpringfieldHospitalCenter();
        assertTrue(instance.validate("FU1234"));
        assertTrue(instance.validate("FE1234"));
        assertTrue(instance.validate("FF1234"));
        assertFalse(instance.validate("FZ1234"));
    }

    @Test
    public void ensureNumbersLastFourCharactersAreDigits() {
        MecanographicNumberSpringfieldHospitalCenter instance = new MecanographicNumberSpringfieldHospitalCenter();
        assertTrue(instance.validate("FU1234"));
        assertFalse(instance.validate("FU123z"));
    }

}
