package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by IvoFerro on 27/04/2017.
 */
public class TopUpTest {

    @Test
    public void ensureTopUpWithNullPkIsNotEqualToAnotherTopUpWithNullPk() throws Exception {
        final AccountCard aCard = new AccountCard(new MecanographicNumber("Dummy"));
        final Money aMoney = Money.euros(50);
        final Username cashier = new Username("Cashier");

        TopUp aTopUp = new TopUp(aCard, aMoney, cashier);

        Transaction aTransaction = new Transaction(aCard, aMoney) {
        };

        assertFalse(aTopUp.equals(aTransaction));
    }

}