package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.util.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @FIXME javadoc
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Entity
public abstract class Transaction implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long pk;
    private MecanographicNumber aMecanographicNumber;
    private Money aMoney;

    @Temporal(TemporalType.DATE)
    private Calendar date;

    protected Transaction() {
        // for ORM only
    }

    public Transaction(MecanographicNumber destinationMecanographicNumber, Money amount) {
        if ((destinationMecanographicNumber == null) || (amount == null)) {
            throw new IllegalStateException("Parameters can't be null.");
        }
        this.aMoney = amount;
        this.aMecanographicNumber = destinationMecanographicNumber;
        this.date = DateTime.now();
    }

    @Override
    public int hashCode() {
        return pk.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || !(o instanceof Transaction)) {
            return false;
        }

        final Transaction other = (Transaction) o;
        return (this.id() != null) && (other.id() != null) && (this.id().equals(other.id()));
    }

    @Override
    public boolean is(Long id) {
        return id().equals(id);
    }

    @Override
    public Long id() {
        return pk;
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
        return (this.pk.equals(that.pk)) && (this.aMecanographicNumber.equals(that.aMecanographicNumber))
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
