/**
 *
 */
package eapli.ecafeteria.domain.users;

import eapli.framework.domain.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author pgsou_000
 *
 */
@Embeddable
public class EmailAddress implements ValueObject, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String	  address;

	public EmailAddress(String address) {
		// FIXME validate invariants
		this.address = address;
	}

	public EmailAddress() {
	}

	@Override
	public String toString() {
		return address;
	}
}
