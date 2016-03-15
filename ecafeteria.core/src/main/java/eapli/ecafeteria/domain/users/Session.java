/**
 *
 */
package eapli.ecafeteria.domain.users;

import eapli.framework.domain.ValueObject;

/**
 * @author pgsou_000
 *
 */
public class Session implements ValueObject {

	private User		 user;
	private SessionToken token;

	public User authenticatedUser() {
		return user;
	}
}
