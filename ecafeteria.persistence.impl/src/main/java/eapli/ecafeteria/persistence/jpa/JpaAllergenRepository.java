package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.Optional;

/**
 * Created by k4rd050 on 27-04-2017.
 */
public class JpaAllergenRepository extends CafeteriaJpaRepositoryBase<Allergen, Designation> implements AllergenRepository {
    @Override
    public Iterable<Allergen> allergens() {
        return findAll();
    }

    @Override
    public Allergen findByName(Designation name) {
        return matchOne("e.name.designation='" + name + "'");
    }
}
