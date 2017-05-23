package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.authz.Username;
import eapli.framework.domain.Money;

import javax.persistence.Entity;

/**
 * Represents a topUp transaction.
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Entity
public class TopUp extends Transaction {

    /**
     * Identifies the cashier who registered the topUp.
     */
    private Username cashier;

    /**
     * Default constructor for object-relational mapping.
     */
    protected TopUp() {
        // for ORM only
    }

    /**
     * Creates a topUp transaction.
     *
     * @param accountCard the account card to topUp
     * @param amount      the amount of the topUp
     * @param aCashier    the username of the cashier who registered the topUp
     */
    public TopUp(AccountCard accountCard, Money amount, Username aCashier) {
        super(accountCard, amount);

        if (amount.amount() <= 0) {
            throw new IllegalStateException("a top up must have a positive amount");
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
