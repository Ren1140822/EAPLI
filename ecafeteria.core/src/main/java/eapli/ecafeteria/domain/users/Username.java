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
public class Username implements ValueObject, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String username;

	public Username(String username) {
		// FIXME validate invariants

		this.username = username;
	}

	public Username() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Username)) {
			return false;
		}

		Username username1 = (Username) o;

		return username != null ? username.equals(username1.username) : username1.username == null;

	}

	@Override
	public String toString() {
		return username;
	}

	@Override
	public int hashCode() {
		return username != null ? username.hashCode() : 0;
	}
}
