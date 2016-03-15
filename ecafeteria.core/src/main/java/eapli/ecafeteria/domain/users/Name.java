package eapli.ecafeteria.domain.users;

import eapli.framework.domain.ValueObject;

public class Name implements ValueObject {
	private final String firstName;
	private final String lastName;

	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
