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

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String	  username;

	public Username(String username) {
		// FIXME validate invariants

		this.username = username;
	}

	@Override
	public String toString() {
		return username;
	}
}
