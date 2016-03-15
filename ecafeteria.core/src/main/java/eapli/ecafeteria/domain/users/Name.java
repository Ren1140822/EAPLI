package eapli.ecafeteria.domain.users;

import eapli.framework.domain.ValueObject;

public class Name implements ValueObject {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String	  firstName;
	private final String	  lastName;

	public Name(String firstName, String lastName) {
		// FIXME validate invariants

		this.firstName = firstName;
		this.lastName = lastName;
	}
}
