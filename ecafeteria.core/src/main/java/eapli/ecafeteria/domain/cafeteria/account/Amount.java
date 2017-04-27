package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Embeddable
public class Amount implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private Double amount;

    protected Amount() {
        // for ORM only
    }

    public Amount(Double amount) {
        if ((amount == null) || (amount.isNaN())) {
            throw new IllegalStateException("Amount must have a valid value.");
        }

        this.amount = amount;
    }

    public Amount add(Double aAmount) {

        return new Amount(this.amount + aAmount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof Amount)) {
            return false;
        }
        if (this == o) {
            return true;
        }

        final Double EPSILON = 0.01d;
        final Amount other = (Amount) o;
        return Math.abs(this.amount - other.amount) < EPSILON;
    }
}
