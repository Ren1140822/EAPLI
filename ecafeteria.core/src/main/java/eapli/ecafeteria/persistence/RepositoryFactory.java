/**
 *
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.persistence.DishTypeRepository;

/**
 * @author pgsou_000
 *
 */
public interface RepositoryFactory {

	UserRepository users();
	DishTypeRepository dishTypes();
}
