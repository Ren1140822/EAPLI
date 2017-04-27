package eapli.ecafeteria.domain.cafeteria.account;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class TransactionTest {

    @Test(expected = IllegalStateException.class)
    public void ensureTransactionHasAmount() {
        new Transaction(null) {
        };
    }

    @Test
    public void ensureTransactionIsEqualsToTheSameInstance() {
        final Transaction aTransaction = new Transaction(50d) {
        };

        assertTrue(aTransaction.equals(aTransaction));
    }

    @Test
    public void ensureTransactionEqualsFailsForDifferentTransaction() {
        final Transaction aTransaction = new Transaction(50d) {
        };
        final Transaction anotherTransaction = new Transaction(55d) {
        };

        assertFalse(aTransaction.equals(anotherTransaction));
    }

}