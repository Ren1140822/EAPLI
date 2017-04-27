package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.Money;
import eapli.framework.domain.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Embeddable
public class Balance implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private Money amount;

    protected Balance() {
        // for ORM only
    }

    public Balance(Money aMoney) {
        if ((aMoney == null) || (aMoney.lessThan(Money.euros(0)))) {
            throw new IllegalStateException("Balance must have a valid value.");
        }

        this.amount = aMoney;
    }

    public Balance add(Money aMoney) {

        Money anotherMoney = this.amount.add(aMoney);
        return new Balance(anotherMoney);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof Balance)) {
            return false;
        }
        if (this == o) {
            return true;
        }

        final Balance other = (Balance) o;

        return this.amount.equals(other.amount);
    }
}
