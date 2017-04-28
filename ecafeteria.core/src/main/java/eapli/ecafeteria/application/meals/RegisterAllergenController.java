package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author guimax
 */
public class RegisterAllergenController {
    
    private final ListAllergensService svcAllergen = new ListAllergensService();
    private final AllergenRepository allergenRepository = PersistenceContext.repositories().allergens();

    public Iterable<Allergen> allAllergen() {
        return this.svcAllergen.allAllergens();
    }
}
