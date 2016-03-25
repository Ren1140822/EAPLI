/**
 *
 */
package eapli.ecafeteria.domain.users;

import java.io.Serializable;

import javax.persistence.Embeddable;

import eapli.framework.domain.ValueObject;

/**
 * @author pgsou_000
 */
@Embeddable
public class EmailAddress implements ValueObject, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String			  address;

	public EmailAddress(String address) {
		// FIXME validate invariants
		this.address = address;
	}

	protected EmailAddress() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof EmailAddress)) {
			return false;
		}

		final EmailAddress that = (EmailAddress) o;

		return address.equals(that.address);

	}

	@Override
	public int hashCode() {
		return address.hashCode();
	}

	@Override
	public String toString() {
		return address;
	}
}
