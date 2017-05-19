package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents a cash register.
 *
 * @FIXME create unit tests
 *
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
@Entity
public class CashRegister implements AggregateRoot<CashRegisterId>, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @Column(unique = true)
    private CashRegisterId id;
    private CashRegisterState state;

    protected CashRegister() {
        // for ORM only
    }

    /**
     * Constructs an instance of CashRegister with CashRegisterState closed and
     * with a CashRegisterId passed as parameter.
     *
     * @param id the id
     */
    public CashRegister(CashRegisterId id) {
        if (id == null) {
            throw new IllegalStateException("A cash register must have an id!");
        }
        this.id = id;
        this.state = CashRegisterState.CLOSED;
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

    /**
     * Modifies a cash register state to closed.
     */
    public void close() {
        if (this.state != CashRegisterState.OPENED) {
            throw new IllegalStateException("Cash Register must be opened before closing!");
        }
        this.state = CashRegisterState.CLOSED;
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
                && this.state.equals(otherCashRegister.state);
    }

    @Override
    public boolean is(CashRegisterId id) {
        return id().equals(id);
    }

    @Override
    public CashRegisterId id() {
        return this.id;
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
