package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.persistence.repositories.Repository;

/**
 * the repository for Dish Types.
 *
 */
public interface DishTypeRepository extends Repository<DishType, Long> {

    /**
     * returns the active dish types
     *
     * @return
     */
    Iterable<DishType> activeDishTypes();

    DishType findByAcronym(String acronym);
}
