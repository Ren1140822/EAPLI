package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author guimax
 */
public class RegisterAllergenController {
    
    private final ListAllergenService svcAllergen = new ListAllergenService();
    private final AllergenRepository allergenRepository = PersistenceContext.repositories().allergens();

    public Iterable<Allergen> allAllergen() {
        return this.svcAllergen.allAllergen();
    }

    public void changeAllergenState(Allergen allergen) throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if (allergen == null) {
            throw new IllegalArgumentException();
        }
        allergen.toogleState();

        Allergen ret = this.AllergenRepository.save(allergen);
    }
}
