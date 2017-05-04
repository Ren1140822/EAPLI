package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 *
 */
@Entity
public class CashRegister implements AggregateRoot<Shift>, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    private CashRegisterId id;
    private CashRegisterState state;
    //@OneToMany(cascade = CascadeType.MERGE)
    private Shift shift;

    protected CashRegister() {
        // for ORM only
    }

    public CashRegister(CashRegisterId id, Shift shift) {
        if (id == null || shift == null) {
            throw new IllegalStateException();
        }
        this.id = id;
        this.state = CashRegisterState.CLOSED;
        this.shift = shift;
    }

    /**
     * Modifies a cash register state to opened.
     */
    public void open() {
        if (this.state != CashRegisterState.CLOSED) {
            throw new IllegalStateException("Cash Register must be closed before opening!");
        }
        this.state = CashRegisterState.OPENED;
    }

    @Override
    public boolean is(Shift idShift) {
        return id().equals(idShift);
    }

    @Override
    public Shift id() {
        return this.shift;
    }

    @Override
    public boolean sameAs(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if ((otherObject == null) || !(otherObject instanceof CashRegister)) {
            return false;
        }

        CashRegister otherCashRegister = (CashRegister) otherObject;

        return this.id.equals(otherCashRegister.id)
                && this.state.equals(otherCashRegister.state)
                && this.shift.equals(otherCashRegister.shift);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if ((other == null) || !(other instanceof CashRegister)) {
            return false;
        }

        CashRegister otherCashRegister = (CashRegister) other;

        return id.equals(otherCashRegister.id);
    }
}
