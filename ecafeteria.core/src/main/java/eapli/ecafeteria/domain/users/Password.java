/**
 *
 */
package eapli.ecafeteria.domain.users;

import eapli.framework.domain.ValueObject;

/**
 * @author pgsou_000
 *
 */
public class Password implements ValueObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public enum PasswordStrength {
		Weak, Good, Excelent,
	}

	private final String password;

	public Password(String password) {
		// FIXME validate invariants
		this.password = password;
	}

	public PasswordStrength strength() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
