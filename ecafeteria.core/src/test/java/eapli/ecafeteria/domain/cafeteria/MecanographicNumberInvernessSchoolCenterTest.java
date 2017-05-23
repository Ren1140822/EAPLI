package eapli.ecafeteria.domain.cafeteria;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Meireles
 */
public class MecanographicNumberInvernessSchoolCenterTest {

    @Test
    public void ensureEmployeeNumbersMustHaveFourCharacters() {
        MecanographicNumberInvernessSchoolCenter instance = new MecanographicNumberInvernessSchoolCenter();
        assertTrue(instance.validate("ZZZZ"));
        assertFalse(instance.validate("ZZZZZ"));
        assertFalse(instance.validate("ZZZ"));
    }

    @Test
    public void ensureEmployeeNumbersMustHaveAllCapitalizedLetters() {
        MecanographicNumberInvernessSchoolCenter instance = new MecanographicNumberInvernessSchoolCenter();
        assertTrue(instance.validate("ZZZZ"));
        assertFalse(instance.validate("ZZZz"));
        assertFalse(instance.validate("ZZZ9"));
    }

    @Test
    public void ensureStudentNumbersMustHaveSixCharacters() {
        MecanographicNumberInvernessSchoolCenter instance = new MecanographicNumberInvernessSchoolCenter();
        assertTrue(instance.validate("123456"));
        assertFalse(instance.validate("1234567"));
        assertFalse(instance.validate("12345"));
    }

    @Test
    public void ensureStudentNumbersMustHaveAllDigits() {
        MecanographicNumberInvernessSchoolCenter instance = new MecanographicNumberInvernessSchoolCenter();
        assertTrue(instance.validate("123456"));
        assertFalse(instance.validate("12345f"));
    }

    @Test
    public void ensureStudentNumbersMustHaveFirstTwoDigitsRepresentingAdmissionYear() {
        MecanographicNumberInvernessSchoolCenter instance = new MecanographicNumberInvernessSchoolCenter();
        assertTrue(instance.validate("123456"));
        assertFalse(instance.validate("563456"));
    }

}
