package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 * Created by k4rd050 on 27-04-2017.
 */
public class InMemoryAllergenRepository extends InMemoryRepository<Allergen, Designation> implements AllergenRepository {

    @Override
    public Iterable<Allergen> allergens() { return findAll(); }

    @Override
    public Allergen findByName(Designation name) { return matchOne(e -> e.name().equals(name)); }

    @Override
    protected Designation newPK(Allergen entity) { return entity.id(); }

}
