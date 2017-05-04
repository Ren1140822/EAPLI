package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class TransactionTest {

    @Test(expected = IllegalStateException.class)
    public void ensureTransactionHasMecanographicNumber() {
        final Money aMoney = Money.euros(50);
        new Transaction(null, aMoney) {
        };
    }

    @Test(expected = IllegalStateException.class)
    public void ensureTransactionHasAmount() {
        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        new Transaction(aMecanographicNumber, null) {
        };
    }

    @Test
    public void ensureTransactionIsEqualsToTheSameInstance() {
        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        final Money aMoney = Money.euros(50);
        final Transaction aTransaction = new Transaction(aMecanographicNumber, aMoney) {
        };

        assertTrue(aTransaction.equals(aTransaction));
    }

    @Test
    public void ensureTransactionEqualsFailsForDifferentTransaction() {
        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        final Money aMoney = Money.euros(50);
        final Transaction aTransaction = new Transaction(aMecanographicNumber, aMoney) {
        };

        final MecanographicNumber anotherMecanographicNumber = new MecanographicNumber("Another dummy");
        final Money anotherMoney = Money.euros(50);
        final Transaction anotherTransaction = new Transaction(anotherMecanographicNumber, anotherMoney) {
        };

        assertFalse(aTransaction.equals(anotherTransaction));
    }

}