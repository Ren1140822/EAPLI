package eapli.ecafeteria.domain.cafeteria.cashregister;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class CashRegisterTest {

    @Test
    public void ensureCashRegisterHasId() {
        new CashRegister(new CashRegisterId("12345"));
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCashRegisterIdCannotBeNull() {
        new CashRegister(null);
    }

    @Test
    public void ensureCashRegisterStateCanBeOpened() {
        CashRegisterId identifier = new CashRegisterId("12345");
        CashRegister cashRegister = new CashRegister(identifier);
        cashRegister.open();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCashRegisterStateCannotBeOpened() {
        CashRegisterId identifier = new CashRegisterId("12345");
        CashRegister cashRegister = new CashRegister(identifier);
        cashRegister.open();
        cashRegister.open();
    }

    @Test
    public void ensureCashRegisterFailsForDifferentCashRegisterIds() throws Exception {

        final CashRegisterId aCashRegisterId = new CashRegisterId("Something");
        final CashRegisterId anotherCashRegisterId = new CashRegisterId("Another something");

        assertFalse(aCashRegisterId.equals(anotherCashRegisterId));
    }

    @Test
    public void ensureCashRegisterPassesForTheSameCashRegisterIds() throws Exception {

        final CashRegisterId aCashRegisterId = new CashRegisterId("Something");
        final CashRegisterId anotherCashRegisterId = new CashRegisterId("Something");

        assertTrue(aCashRegisterId.equals(anotherCashRegisterId));
    }

    @Test
    public void ensureCashRegisterIsTheSameAsTheSameCashRegister() throws Exception {

        final CashRegisterId aCashRegisterId = new CashRegisterId("Something");
        final CashRegisterId anotherCashRegisterId = new CashRegisterId("Something");
        final CashRegister aCashRegister = new CashRegister(aCashRegisterId);
        final CashRegister anotherCashRegister = new CashRegister(anotherCashRegisterId);

        assertTrue(aCashRegister.sameAs(anotherCashRegister));
    }

    @Test
    public void ensureCashRegisterIsNotTheSameAsTheSameCashRegister() throws Exception {

        final CashRegisterId aCashRegisterId = new CashRegisterId("Something");
        final CashRegisterId anotherCashRegisterId = new CashRegisterId("Another something");
        final CashRegister aCashRegister = new CashRegister(aCashRegisterId);
        final CashRegister anotherCashRegister = new CashRegister(anotherCashRegisterId);

        assertFalse(aCashRegister.sameAs(anotherCashRegister));
    }

}
