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
	private String firstName;
	private String lastName;
	public Name(String firstName, String lastName) {
		// FIXME validate invariants

		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Name() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Name)) {
			return false;
		}

		Name name = (Name) o;

		if (!firstName.equals(name.firstName)) {
			return false;
		}
		return lastName.equals(name.lastName);

	}

	@Override
	public int hashCode() {
		int result = firstName.hashCode();
		result = 31 * result + lastName.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
