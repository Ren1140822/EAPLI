/**
 *
 */
package eapli.ecafeteria.domain.users;


import eapli.framework.domain.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author pgsou_000
 */
@Embeddable
public class Password implements ValueObject, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String password;

	public Password() {
	}

	public Password(String password) {
		// FIXME validate invariants
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Password)) {
			return false;
		}

		Password password1 = (Password) o;

		return password.equals(password1.password);

	}

	@Override
	public int hashCode() {
		return password.hashCode();
	}

	/**
	 * Check how strong a password is
	 *
	 * @return how strong a password is
	 */
	public PasswordStrength strength() {
		PasswordStrength passwordStrength = PasswordStrength.Weak;
		if (3 > password.length()) {
			passwordStrength = PasswordStrength.Weak;
		}
		return passwordStrength;
		//TODO implement the rest of the method
	}

	@Override
	public String toString() {
		return password;
	}

	public enum PasswordStrength {
		Weak, Good, Excelent,
	}
}
