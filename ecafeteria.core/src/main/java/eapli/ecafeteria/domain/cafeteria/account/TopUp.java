package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;

import javax.persistence.Entity;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Entity
public class TopUp extends Transaction {

    protected TopUp() {
        // for ORM only
    }

    public TopUp(MecanographicNumber destinationMecanographicNumber, Money amount) {
        super(destinationMecanographicNumber, amount);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && (o instanceof TopUp);
    }
}
