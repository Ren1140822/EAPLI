/**
 *
 */
package eapli.ecafeteria.domain.users;

import eapli.framework.domain.ValueObject;

/**
 * @author pgsou_000
 *
 */
public class Username implements ValueObject {

	private final String username;

	public Username(String username) {
		this.username = username;
	}
}
