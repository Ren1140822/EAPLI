package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.persistence.*;

/**
 * @TODO why is the AccountCard a separate aggregate? is this justified in the
 * design of the use case?
 * @FIXME javadoc
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Entity
public class AccountCard implements AggregateRoot<MecanographicNumber>, Observer, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private MecanographicNumber mecanographicNumber;

    private Balance balance;

    protected AccountCard() {
        // for ORM only
    }

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
