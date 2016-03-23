package eapli.ecafeteria.domain.users;

import eapli.framework.domain.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Name implements ValueObject, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String	  firstName;
	private String	  lastName;

	public Name(String firstName, String lastName) {
		// FIXME validate invariants

		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Name() {
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
