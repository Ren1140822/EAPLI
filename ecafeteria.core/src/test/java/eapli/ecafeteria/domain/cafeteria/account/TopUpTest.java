package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.Money;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by IvoFerro on 27/04/2017.
 */
public class TopUpTest {

    @Test
    public void ensureTopUpIsNotEqualsToAnotherTransaction() throws Exception {
        TopUp aTopUp = new TopUp(Money.euros(50));
        Transaction aTransaction = new Transaction(Money.euros(50)) {
        };

        assertFalse(aTopUp.equals(aTransaction));
    }

    @Test
    public void ensureTopUpIsNotEqualsToAnotherTopUp() throws Exception {
        TopUp aTopUp = new TopUp(Money.euros(50));
        TopUp anotherTopUp = new TopUp(Money.euros(55)) {
        };

        assertFalse(aTopUp.equals(anotherTopUp));
    }

}