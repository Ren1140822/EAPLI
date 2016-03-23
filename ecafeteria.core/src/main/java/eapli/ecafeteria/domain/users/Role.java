/**
 *
 */
package eapli.ecafeteria.domain.users;

import java.io.Serializable;
import java.util.Calendar;

import eapli.framework.domain.ValueObject;
import eapli.util.DateTime;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author pgsou_000
 *
 */

public class Role implements ValueObject, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private RoleType	  type;
	@Temporal(TemporalType.DATE)
	private Calendar	  assignedOn;

	public Role(RoleType type) {
		this(type, DateTime.now());
	}

	public Role(RoleType type, Calendar assignedOn) {
		// FIXME validate invariants
		this.type = type;
		this.assignedOn = assignedOn;
	}

	public Role() {
	}

	@Override
	public String toString() {
		return type + "@" + assignedOn;
	}

	public RoleType type() {
		return type;
	}
}
