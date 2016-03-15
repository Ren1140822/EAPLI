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
	private final RoleType type;
	private final Calendar assignedOn;

	public Role(RoleType type) {
		// FIXME validate invariants
		this.type = type;
		assignedOn = DateTime.now();
	}
}
