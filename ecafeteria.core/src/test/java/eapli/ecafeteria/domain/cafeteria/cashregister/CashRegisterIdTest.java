package eapli.ecafeteria.domain.cafeteria.cashregister;

import org.junit.Test;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class CashRegisterIdTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureCashRegisterIdIsDefined() {
        new CashRegisterId(null);
    }

}
