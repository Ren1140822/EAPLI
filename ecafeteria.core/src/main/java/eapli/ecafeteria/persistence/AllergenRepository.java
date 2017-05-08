package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * Created by k4rd050 on 27-04-2017.
 */
public interface AllergenRepository extends DataRepository<Allergen, Designation> {

    /**
     * returns all existing allergens
     *
     * @todo there is already a findAll method in DataRepository
     * @return
     */
    Iterable<Allergen> allergens();

    Allergen findByName(Designation name);

}
