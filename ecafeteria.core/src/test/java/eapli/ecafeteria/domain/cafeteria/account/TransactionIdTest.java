package eapli.ecafeteria.domain.cafeteria.account;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the class Transaction ID.
 *
 * @author Ivo Ferro
 */
public class TransactionIdTest {

    /**
     * Tests if a TransactionId is equals to itself.
     */
    @Test
    public void ensureTheSameInstanceOfTransactionIdIsEqualsToItself() {
        TransactionId aTransactionId = new TransactionId();
        TransactionId theSameTransactionId = aTransactionId;

        assertTrue(aTransactionId.equals(theSameTransactionId));
    }

    /**
     * Tests if different TransactionIds are not equals.
     */
    @Test
    public void ensureDifferentTransactionsIdsAreNotEquals() {
        TransactionId aTransactionId = new TransactionId();
        TransactionId anotherTransactionId = new TransactionId();

        assertFalse(aTransactionId.equals(anotherTransactionId));
    }

}