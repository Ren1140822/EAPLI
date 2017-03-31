package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.impl.jpa.JpaNotRunningInContainerBaseRepository;

/**
 * In this example we want to show the use of
 * JpaNotRunningIncontainerBaseRepository
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaDishRepository extends JpaNotRunningInContainerBaseRepository<Dish, Designation> implements DishRepository {

    JpaDishRepository(String persistenceUnitName) {
        super(persistenceUnitName);
    }

    JpaDishRepository() {
        super(Application.settings().getPersistenceUnitName());
    }

    @Override
    public Dish findByName(Designation name) {
        // TODO use parameters instead of string concatenation
        return matchOne("e.name.designation='" + name + "'");
    }
}
