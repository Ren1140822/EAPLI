/**
 *
 */
package eapli.ecafeteria.domain.users;

import java.util.Calendar;

import eapli.framework.domain.ValueObject;

/**
 * @author pgsou_000
 *
 */
public class Role implements ValueObject {
	private RoleType type;
	private Calendar assignedOn;
}
