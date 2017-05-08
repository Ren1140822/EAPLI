package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the class Transaction.
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class TransactionTest {

    /**
     * Ensures that a transaction has a mecanographic number.
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTransactionHasMecanographicNumber() {
        final Money aMoney = Money.euros(50);
        new Transaction(null, aMoney) {
        };
    }

    /**
     * Ensures that a transaction has an amount.
     */
    @Test(expected = IllegalStateException.class)
    public void ensureTransactionHasAmount() {
        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        new Transaction(aMecanographicNumber, null) {
        };
    }

    /**
     * Ensures a transaction is equals to itself.
     */
    @Test
    public void ensureTransactionIsEqualsToTheSameInstance() {
        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        final Money aMoney = Money.euros(50);
        final Transaction aTransaction = new Transaction(aMecanographicNumber, aMoney) {
        };
        final Transaction theSameTransaction = aTransaction;

        assertTrue(aTransaction.equals(theSameTransaction));
    }

    /**
     * Ensure that different transactions are not equals.
     */
    @Test
    public void ensureTransactionIsNotEqualsToDifferentTransaction() {
        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        final Money aMoney = Money.euros(50);
        final Transaction aTransaction = new Transaction(aMecanographicNumber, aMoney) {
        };

        final MecanographicNumber anotherMecanographicNumber = new MecanographicNumber("Another dummy");
        final Money anotherMoney = Money.euros(100);
        final Transaction anotherTransaction = new Transaction(anotherMecanographicNumber, anotherMoney) {
        };

        assertFalse(aTransaction.equals(anotherTransaction));
    }

}