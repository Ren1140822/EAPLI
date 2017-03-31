package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Dish;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * In this example we want to show the use of
 * JpaNotRunningIncontainerBaseRepository
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface DishRepository extends DataRepository<Dish, Designation>, TransactionalContext {

    Dish findByName(Designation name);
}
