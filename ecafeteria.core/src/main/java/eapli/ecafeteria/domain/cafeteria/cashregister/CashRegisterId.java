package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * @FIXME javadoc
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
@Embeddable
public class CashRegisterId implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private String identifier;

    protected CashRegisterId() {
        // for ORM only
    }

    public CashRegisterId(String identifier) {
        //TODO is an empty string allowed?
        if (identifier == null || identifier == "") {
            throw new IllegalArgumentException("Identifier must be defined");
        }
        this.identifier = identifier;
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if ((other == null) || !(other instanceof CashRegisterId)) {
            return false;
        }
        if (this == other) {
            return true;
        }

        final CashRegisterId otherCashRegister = (CashRegisterId) other;

        return this.identifier.equals(otherCashRegister.identifier);
    }
}
