package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class AccountCardTest {

    @Test(expected = IllegalStateException.class)
    public void ensureAccountCardHasCafeteriaUser() {
        new AccountCard(null);
    }

    @Test
    public void ensureAccountCardPassesForTheSameMecanographicNumber() throws Exception {

        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        final MecanographicNumber anotherMecanographicNumber = new MecanographicNumber("Dummy");

        assertTrue(aMecanographicNumber.equals(anotherMecanographicNumber));
    }

    @Test
    public void ensureAccountCardFailsForDifferentMecanographicNumber() throws Exception {

        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        final MecanographicNumber anotherMecanographicNumber = new MecanographicNumber("Another dummy");

        assertFalse(aMecanographicNumber.equals(anotherMecanographicNumber));
    }

    @Test
    public void ensureAccountCardIsTheSameAsTheSameAccountCard() throws Exception {

        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        final MecanographicNumber anotherMecanographicNumber = new MecanographicNumber("Dummy");
        final AccountCard aAccountCard = new AccountCard(aMecanographicNumber);
        final AccountCard anotherAccountCard = new AccountCard(anotherMecanographicNumber);

        assertTrue(aAccountCard.sameAs(anotherAccountCard));
    }

    @Test
    public void ensureAccountCardIsNotTheSameAsTheSameSystemUser() throws Exception {

        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        final MecanographicNumber anotherMecanographicNumber = new MecanographicNumber("Another dummy");
        final AccountCard aAccountCard = new AccountCard(aMecanographicNumber);
        final AccountCard anotherAccountCard = new AccountCard(anotherMecanographicNumber);

        assertFalse(aAccountCard.sameAs(anotherAccountCard));
    }

}