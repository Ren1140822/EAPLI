/**
 *
 */
package eapli.ecafeteria.domain.users;

import eapli.framework.domain.ValueObject;

/**
 * @author pgsou_000
 *
 */
public class SessionToken implements ValueObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String	  token;

	public SessionToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return token;
	}
}
