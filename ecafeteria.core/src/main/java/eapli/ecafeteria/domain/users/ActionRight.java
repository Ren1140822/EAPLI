/**
 *
 */
package eapli.ecafeteria.domain.users;

import java.util.Collection;

/**
 * @author pgsou_000
 *
 */
public enum ActionRight {
	Administer, SelectMeal, ManageKitchen, ManageMenus, Sale,;

	public boolean canBePerformedBy(Collection<RoleType> roles) {
		if (this == Administer && roles.contains(RoleType.Admin)) {
			return true;
		}
		if (this == SelectMeal && roles.contains(RoleType.User)) {
			return true;
		}
		if (this == ManageKitchen && roles.contains(RoleType.KitchenManager)) {
			return true;
		}
		if (this == ManageMenus && roles.contains(RoleType.Nutricionist)) {
			return true;
		}
		if (this == Sale && roles.contains(RoleType.Cashier)) {
			return true;
		}
		return false;
	}
}
