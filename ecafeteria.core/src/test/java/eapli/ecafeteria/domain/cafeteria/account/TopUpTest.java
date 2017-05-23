package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Unit tests for the TopUp class.
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class TopUpTest {

    @Test(expected = IllegalStateException.class)
    public void ensureTopUpHasCashier() {
        final AccountCard aCard = new AccountCard(new MecanographicNumber("Dummy"));
        final Money aMoney = Money.euros(50);

        new TopUp(aCard, aMoney, null);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureTopUpHasPositiveAmount() {
        final AccountCard aCard = new AccountCard(new MecanographicNumber("Dummy"));
        final Money aMoney = Money.euros(20).negate();
        final Username cashier = new Username("Cashier");

        new TopUp(aCard, aMoney, cashier);
    }

    @Test
    public void ensureTopUpWithNullPkIsNotEqualToAnotherTopUpWithNullPk() {
        final AccountCard aCard = new AccountCard(new MecanographicNumber("Dummy"));
        final Money aMoney = Money.euros(50);
        final Username cashier = new Username("Cashier");

        TopUp aTopUp = new TopUp(aCard, aMoney, cashier);

        Transaction aTransaction = new Transaction(aCard, aMoney) {
        };

        assertFalse(aTopUp.equals(aTransaction));
    }
}