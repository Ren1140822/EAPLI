package eapli.ecafeteria.domain.users;

import java.io.Serializable;

import javax.persistence.Embeddable;

import eapli.framework.domain.ValueObject;

@Embeddable
public class Name implements ValueObject, Serializable {
	/**
	 *

	 */
	private static final long serialVersionUID = 1L;
	private String			  firstName;
	private String			  lastName;

	public Name(String firstName, String lastName) {
		// FIXME validate invariants
		this.firstName = firstName;
		this.lastName = lastName;
	}

	protected Name() {
	}

        public String firstName()
        {
            return this.firstName;
        }
        
        public String lastName()
        {
            return this.lastName;
        }
        
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Name)) {
			return false;
		}

		final Name name = (Name) o;

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
