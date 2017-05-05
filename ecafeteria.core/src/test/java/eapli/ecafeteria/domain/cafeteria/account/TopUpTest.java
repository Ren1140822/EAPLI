package eapli.ecafeteria.domain.cafeteria.account;

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
        final MecanographicNumber aMecanographicNumber = new MecanographicNumber("Dummy");
        final Money aMoney = Money.euros(50);
        TopUp aTopUp = new TopUp(aMecanographicNumber, aMoney);

        Transaction aTransaction = new Transaction(aMecanographicNumber, aMoney) {
        };

        assertFalse(aTopUp.equals(aTransaction));
    }

}