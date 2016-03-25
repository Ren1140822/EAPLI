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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Role)) return false;

		Role role = (Role) o;

		return type == role.type;

	}

	@Override
	public int hashCode() {
		return type.hashCode();
	}

	@Override

	public String toString() {
		return type + "@" + assignedOn;
	}

	public RoleType type() {
		return type;
	}
}
