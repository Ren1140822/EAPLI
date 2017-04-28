package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.Money;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class BalanceTest {

    @Test(expected = IllegalStateException.class)
    public void ensureAmountHasAmount() {
        new Balance(null);
    }

    @Test
    public void ensureAmountIsEqualsToTheSameInstance() {
        final Money aMoney = Money.euros(50);
        final Balance aBalance = new Balance(aMoney);

        assertTrue(aBalance.equals(aBalance));
    }

    @Test
    public void ensureAmountIsEqualsToTheSameAmount() {
        final Money aMoney = Money.euros(50);

        final Balance aBalance = new Balance(aMoney);
        final Balance anotherBalance = new Balance(aMoney);

        assertTrue(aBalance.equals(anotherBalance));
    }

    @Test
    public void ensureAmountEqualsFailsForDifferentAmount() {
        final Money aMoney = Money.euros(50);
        final Money anotherMoney = Money.euros(55);

        final Balance aBalance = new Balance(aMoney);
        final Balance anotherBalance = new Balance(anotherMoney);

        assertFalse(aBalance.equals(anotherBalance));
    }
}