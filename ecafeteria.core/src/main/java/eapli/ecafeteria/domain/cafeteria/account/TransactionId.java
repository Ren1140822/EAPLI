package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.ddd.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a unique identifier to identify a transaction.
 *
 * @author Ivo Ferro
 */
@Embeddable
public class TransactionId implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the transaction.
     */
    private UUID identifier;

    /**
     * Constructs a unique identifier for a transaction.
     */
    public TransactionId() {
        //FIXME
        //@Meireles
        // Check method "randomString" from Strings class (eapli.util.Strings)
        // @danieljgoncalves & @ivoferroo
        // REPLY : We used UUID instead, because "randomString" doesn't guarantee being unique.
        // @Meireles check: https://docs.oracle.com/javase/7/docs/api/java/util/UUID.html

        this.identifier = UUID.randomUUID();
    }

    /**
     * Compares if this instance is equals to a given object.
     *
     * @param o object to be compared
     * @return true if it is equals to this instance, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }

        TransactionId that = (TransactionId) o;

        return identifier.equals(that.identifier);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return identifier.hashCode();
    }
}
