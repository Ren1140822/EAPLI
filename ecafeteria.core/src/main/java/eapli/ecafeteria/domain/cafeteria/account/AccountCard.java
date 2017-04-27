package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.framework.domain.AggregateRoot;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Entity
public class AccountCard implements AggregateRoot<CafeteriaUser>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long pk;

    // business ID
    @Column(unique = true)
    @OneToOne
    private CafeteriaUser owner;
    private Amount balance;

    protected AccountCard() {
        // for ORM only
    }

    public AccountCard(CafeteriaUser owner) {
        if (owner == null) {
            throw new IllegalStateException("Owner can't be null");
        }
        this.owner = owner;
        this.balance = new Amount(0d);
    }

    /**
     * Adds a transaction amount to the account card balance.
     *
     * @param aAmount the amount to add
     */
    public void topUp(Double aAmount) {
        this.balance = this.balance.add(aAmount);
    }

    @Override
    public boolean is(CafeteriaUser id) {
        return id().equals(id);
    }

    @Override
    public CafeteriaUser id() {
        return this.owner;
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

        return this.owner.equals(otherCard.owner) && this.balance.equals(otherCard.balance);
    }

    @Override
    public int hashCode() {
        return owner.hashCode();
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

        return owner.equals(otherCard.owner);
    }
}
