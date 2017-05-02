/**
 *
 */
package eapli.framework.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import eapli.framework.domain.ddd.ValueObject;
import eapli.util.Strings;

/**
 * Generic name concept
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Embeddable
public class Designation implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    private String theDesignation;

    /**
     * protected constructor. to construct a new Designation instance use the
     * valueOf() method
     *
     * @param name
     */
    protected Designation(String name) {
	if (Strings.isNullOrEmpty(name)) {
	    throw new IllegalStateException("Name should neither be null nor empty");
	}
	theDesignation = name;
    }

    /**
     * factory method for obtaining Designation value objects.
     *
     *
     * @param name
     * @return
     */
    public static Designation valueOf(String name) {
	return new Designation(name);
    }

    protected Designation() {
	// for ORM
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) {
	    return true;
	}
	if (!(o instanceof Designation)) {
	    return false;
	}

	final Designation other = (Designation) o;

	return theDesignation.equals(other.theDesignation);
    }

    @Override
    public String toString() {
	return theDesignation;
    }

    @Override
    public int hashCode() {
	return theDesignation.hashCode();
    }
}
