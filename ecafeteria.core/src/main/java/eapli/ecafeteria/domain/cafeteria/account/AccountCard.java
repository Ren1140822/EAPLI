package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.AggregateRoot;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents an account card of a cafeteria user. (issue #22 explains the design decisions).
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Entity
public class AccountCard implements AggregateRoot<MecanographicNumber>, Observer, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    /**
     * The mecanographic number that identifies the account card.
     */
    @EmbeddedId
    private MecanographicNumber mecanographicNumber;

    /**
     * The balance on the account card.
     */
    private Balance balance;

    /**
     * Default constructor for object-relational mapping.
     */
    protected AccountCard() {
        // for ORM only
    }

    /**
     * Creates an account card associated to a cafeteria user.
     *
     * @param mecanographicNumber the mecanographic number to associate the cafeteria user
     */
    public AccountCard(MecanographicNumber mecanographicNumber) {
        if (mecanographicNumber == null) {
            throw new IllegalStateException("Mecanographic number can't be null");
        }

        this.mecanographicNumber = mecanographicNumber;

        Money startMoney = Money.euros(0);
        this.balance = new Balance(startMoney);
    }

    /**
     * Updates the card's balance taking into account the new transaction.
     *
     * @param aTransaction The transaction to be accounted in the balance.
     */
    public void aggregate(Transaction aTransaction) {
        this.balance = this.balance.add(aTransaction.value());
    }

    /**
     * Retrieves the account card balance.
     *
     * @return the account card balance
     */
    public Balance balance() {
        return this.balance;
    }

    @Override
    public boolean is(MecanographicNumber id) {
        return id().equals(id);
    }

    @Override
    public MecanographicNumber id() {
        return this.mecanographicNumber;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) {
            return true;
        }
        if ((other == null) || !(other instanceof AccountCard)) {
            return false;
        }

        AccountCard otherCard = (AccountCard) other;

        return this.mecanographicNumber.equals(otherCard.mecanographicNumber) && this.balance.equals(otherCard.balance);
    }

    @Override
    public int hashCode() {
        return mecanographicNumber.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if ((other == null) || !(other instanceof AccountCard)) {
            return false;
        }

        AccountCard otherCard = (AccountCard) other;

        return mecanographicNumber.equals(otherCard.mecanographicNumber);
    }

    /**
     * It updates the balance when a transaction notifies.
     *
     * @param o The transaction that notified the card.
     * @param arg It is not used and it has null value.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o == null || !(o instanceof Transaction)) {
            throw new IllegalArgumentException("unrecognized observable");
        }

        aggregate((Transaction) o);
    }

}
