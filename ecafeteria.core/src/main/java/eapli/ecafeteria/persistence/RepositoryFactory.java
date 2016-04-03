/**
 *
 */
package eapli.ecafeteria.persistence;

/**
 * @author pgsou_000
 *
 */
public interface RepositoryFactory {

	UserRepository users();
	DishTypeRepository dishTypes();
        OrganicUnitRepository organicUnits();
        CafeteriaUserRepository cafeteriaUsers();
}
