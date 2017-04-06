package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.framework.domain.Designation;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaDishRepository extends CafeteriaJpaRepositoryBase<Dish, Designation> implements DishRepository {

    @Override
    public Dish findByName(Designation name) {
        // TODO use parameters instead of string concatenation
        return matchOne("e.name.designation='" + name + "'");
    }
}
