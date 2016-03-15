/**
 *
 */
package eapli.ecafeteria.domain.users;

import eapli.framework.domain.ValueObject;

/**
 * @author pgsou_000
 *
 */
public class EmailAddress implements ValueObject {

	private final String address;

	public EmailAddress(String address) {
		this.address = address;
	}
}
