package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.Money;
import eapli.framework.domain.ValueObject;
import eapli.util.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Entity
public abstract class Transaction implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long pk;

    private Money aMoney;

    @Temporal(TemporalType.DATE)
    private Calendar date;

    protected Transaction() {
        // for ORM only
    }

    public Transaction(Money aMoney) {
        if (aMoney == null) {
            throw new IllegalStateException("Money can't be null.");
        }
        this.aMoney = aMoney;
        this.date = DateTime.now();
    }

    @Override
    public int hashCode() {
        int result = aMoney.hashCode();
        result = 31 * result + date.hashCode();
        return result;
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
        return this.aMoney.equals(other.aMoney) && this.date.equals(other.date);
    }
}
