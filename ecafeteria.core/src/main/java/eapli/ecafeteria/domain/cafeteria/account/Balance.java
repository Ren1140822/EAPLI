package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Represents a account card balance.
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Embeddable
public class Balance implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The balance's amount
     */
    private Money amount;

    /**
     * Default constructor for object-relational mapping.
     */
    protected Balance() {
        // for ORM only
    }

    /**
     * Creates a balance with a certain starting amount.
     *
     * @param aMoney the starting amount
     */
    public Balance(Money aMoney) {
        if ((aMoney == null) || (aMoney.lessThan(Money.euros(0)))) {
            throw new IllegalStateException("Balance must have a valid value.");
        }

        this.amount = aMoney;
    }

    /**
     * Adds an amount to the balance.
     *
     * @param aMoney the amount to add
     * @return a new balance object
     */
    public Balance add(Money aMoney) {

        Money anotherMoney = this.amount.add(aMoney);
        return new Balance(anotherMoney);
    }

    /**
     * Retrieves the balance amount.
     *
     * @return the balance amount
     */
    public Money amount(){
        return this.amount;
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
    
    public boolean hasEnoughBalance(Money price){
        return this.amount().lessThan(price);
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
