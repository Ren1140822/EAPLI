package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * Created by k4rd050 on 28-04-2017.
 */
public class AddAllergensService {
    AllergenRepository rep = PersistenceContext.repositories().allergens();

    public boolean registerAllergen(String name, String description) throws DataConcurrencyException, DataIntegrityViolationException {
        Allergen allergen = new Allergen(Designation.valueOf(name),description);
        Allergen createdAllergen = rep.save(allergen);
        return (createdAllergen!=null);
    }
}
