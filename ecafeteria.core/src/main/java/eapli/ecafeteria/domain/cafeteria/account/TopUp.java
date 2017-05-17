package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.authz.Username;
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

    /**
     * Identifies the cashier who registered the topUp.
     */
    private Username cashier;

    protected TopUp() {
        // for ORM only
    }

    public TopUp(AccountCard accountCard, Money amount, Username aCashier) {
        super(accountCard, amount);

        if (amount.amount() < 0) {
            throw new IllegalStateException("a top up must not have a negative amount");
        }
        if (aCashier == null) {
            throw new IllegalStateException("cashier username can't be null.");
        }
        cashier = aCashier;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && (o instanceof TopUp);
    }
}
