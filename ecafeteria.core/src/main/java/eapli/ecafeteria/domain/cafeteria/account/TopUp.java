package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import javax.persistence.Entity;

/**
 * @FIXME javadoc
 * @FIXME is this an entity, a value object or an aggregate?
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Entity
public class TopUp extends Transaction {

    protected TopUp() {
        // for ORM only
    }

    public TopUp(AccountCard accountCard, Money amount) {
        super(accountCard, amount);
        //FIXME
        //@author Meireles
        // Should a validation of the amount be made? A TopUp can have a negative amount? Or a zero amount?
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && (o instanceof TopUp);
    }
}
