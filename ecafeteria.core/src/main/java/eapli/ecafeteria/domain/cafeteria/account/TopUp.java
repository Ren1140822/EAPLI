package eapli.ecafeteria.domain.cafeteria.account;

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

    public TopUp(Double aAmount) {
        super(aAmount);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && (o instanceof TopUp);
    }
}
