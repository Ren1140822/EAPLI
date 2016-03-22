/**
 *
 */
package eapli.ecafeteria.domain.users;

import java.util.Calendar;

import eapli.framework.domain.ValueObject;
import eapli.util.DateTime;

/**
 * @author pgsou_000
 *
 */
public class Role implements ValueObject {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final RoleType	  type;
	private final Calendar	  assignedOn;

	public Role(RoleType type) {
		this(type, DateTime.now());
	}

	public Role(RoleType type, Calendar assignedOn) {
		// FIXME validate invariants
		this.type = type;
		this.assignedOn = assignedOn;
	}

	@Override
	public String toString() {
		return type + "@" + assignedOn;
	}

	public RoleType type() {
		return type;
	}
}
