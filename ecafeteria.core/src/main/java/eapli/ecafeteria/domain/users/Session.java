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
//TODO NMB:Ask who keeps the session?
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private User			  user;
	private SessionToken	  token;

	public User authenticatedUser() throws NoUserSessionInitiatedException {
		if ( null == user)
			throw new NoUserSessionInitiatedException("No user session initiated");
		return user;
	}

	public Session(User user) {
		this.user = user;
	}
}
