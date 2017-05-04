package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 * @TODO why is the AccountCard a separate aggregate? is this justified in the
 * design of the use case?
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
@Entity
public class AccountCard implements AggregateRoot<MecanographicNumber>, Serializable {

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
     * Adds money to the account card balance.
     *
     * @param aMoney the money to add
     */
    public void topUp(Money aMoney) {
        this.balance = this.balance.add(aMoney);
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
}
