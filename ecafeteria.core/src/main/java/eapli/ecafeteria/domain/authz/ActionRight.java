/**
 *
 */
package eapli.ecafeteria.domain.authz;

import java.util.Collection;

/**
 * @author Paulo Gandra Sousa
 *
 */
public enum ActionRight {
    ADMINISTER, SELECT_MEAL, MANAGE_KITCHEN, MANAGE_MENUS, SALE, MANAGE_DELIVERY,;

    /**
     * checks if this action right can be performed by a user with the specified
     * role types
     * 
     * @param roles
     * @return
     */
    public boolean canBePerformedBy(Collection<RoleType> roles) {
        if (this == ADMINISTER && roles.contains(RoleType.Admin)) {
            return true;
        }
        if (this == SELECT_MEAL && roles.contains(RoleType.User)) {
            return true;
        }
        if (this == MANAGE_KITCHEN && roles.contains(RoleType.KitchenManager)) {
            return true;
        }
        if (this == MANAGE_MENUS && roles.contains(RoleType.MenuManager)) {
            return true;
        }
        if (this == SALE && roles.contains(RoleType.Cashier)) {
            return true;
        }
        if (this == MANAGE_DELIVERY && roles.contains(RoleType.Cashier)) {
            return true;
        }
        return false;
    }
}
