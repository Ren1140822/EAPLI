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

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private User			  user;
	private SessionToken	  token;

	public User authenticatedUser() {
		return user;
	}
}
