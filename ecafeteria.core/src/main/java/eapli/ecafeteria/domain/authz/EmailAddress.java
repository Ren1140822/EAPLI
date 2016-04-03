/**
 *
 */
package eapli.ecafeteria.domain.authz;

import java.io.Serializable;

import javax.persistence.Embeddable;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

/**
 * @author pgsou_000
 */
@Embeddable
public class EmailAddress implements ValueObject, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String address;

    public EmailAddress(String address) {
        if (Strings.isNullOrEmpty(address)) {
            throw new IllegalStateException("email address should neither be null nor empty");
        }
        // FIXME validate invariants, i.e., email regular expression
        this.address = address;
    }

    protected EmailAddress() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailAddress)) {
            return false;
        }

        final EmailAddress that = (EmailAddress) o;

        return this.address.equals(that.address);

    }

    @Override
    public int hashCode() {
        return this.address.hashCode();
    }

    @Override
    public String toString() {
        return this.address;
    }
}
