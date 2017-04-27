package eapli.ecafeteria.domain.cafeteria.account;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class AmountTest {

    @Test(expected = IllegalStateException.class)
    public void ensureAmountHasAmount() {
        new Amount(null);
    }

    @Test
    public void ensureAmountIsEqualsToTheSameInstance() {
        final Amount aAmount = new Amount(50d);

        assertTrue(aAmount.equals(aAmount));
    }

    @Test
    public void ensureAmountIsEqualsToTheSameAmount() {
        final Amount aAmount = new Amount(50d);
        final Amount anotherAmount = new Amount(50d);

        assertTrue(aAmount.equals(anotherAmount));
    }

    @Test
    public void ensureAmountEqualsFailsForDifferentAmount() {
        final Amount aAmount = new Amount(50d);
        final Amount anotherAmount = new Amount(55d);

        assertFalse(aAmount.equals(anotherAmount));
    }
}