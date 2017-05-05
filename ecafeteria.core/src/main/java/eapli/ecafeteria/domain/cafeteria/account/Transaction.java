package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.ValueObject;
import eapli.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

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
        int result = aMecanographicNumber.hashCode();
        result = 31 * result + aMoney.hashCode();
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
        return (this.aMecanographicNumber.equals(other.aMecanographicNumber))
                && (this.aMoney.equals(other.aMoney)) && (this.date.equals(other.date));
    }

    /**
     * The transacted money amount.
     * 
     * @return It returns the money value of the transaction.
     */
    public Money value(){
        return aMoney;
    }

}
