package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Observable;
import javax.persistence.*;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 * <p>
 * Represents a generic transaction.
 * </p>
 */
@Entity
public abstract class Transaction extends Observable implements AggregateRoot<TransactionId>, Serializable {

    private static final long serialVersionUID = 1L;
    @Version
    private Long version;
    @Id
    @GeneratedValue
    private Long pk;

    /**
     * The domain identifier for the transaction.
     */
    private TransactionId aTransactionId;
    /**
     * The account card of the card account.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    private AccountCard aCard;
    /**
     * The amount of money.
     */
    private Money aMoney;
    /**
     * The date when the transaction occurred.
     */
    @Temporal(TemporalType.DATE)
    private Calendar date;

    /**
     * Default constructor for object-relational mapping.
     */
    protected Transaction() {
        // for ORM only
    }

    /**
     * Creates a transaction receiving the destination mecanographic number and
     * the amount to move.
     *
     * @param accountCard the destination mecanographic number
     * @param amount the amount of the transaction.
     */
    public Transaction(AccountCard accountCard, Money amount) {
        if ((accountCard == null) || (amount == null)) {
            throw new IllegalStateException("Parameters can't be null.");
        }
        this.aTransactionId = new TransactionId();
        this.aMoney = amount;
        this.aCard = accountCard;
        this.date = DateTime.now();
        addObserver(aCard);
        setChanged();
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return aTransactionId.hashCode();
    }

    /**
     * Compares if the transaction is equals to other transaction.
     *
     * @param o other transaction to be compared.
     * @return true if they are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof Transaction)) {
            return false;
        }
        if (this == o) {
            return true;
        }

        final Transaction other = (Transaction) o;
        return (this.aTransactionId.equals(other.aTransactionId));
    }

    @Override
    public boolean is(TransactionId id) {
        return id().equals(id);
    }

    @Override
    public TransactionId id() {
        return aTransactionId;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof CafeteriaUser)) {
            return false;
        }

        final Transaction that = (Transaction) other;
        if (this == that) {
            return true;
        }
        return (this.aTransactionId.equals(((Transaction) other).aTransactionId))
                && (this.aCard.equals(that.aCard))
                && (this.aMoney.equals(that.aMoney)) && (this.date.equals(that.aMoney));
    }

    /**
     * The transacted money amount.
     *
     * @return It returns the money value of the transaction.
     */
    public Money value() {
        return aMoney;
    }

}
