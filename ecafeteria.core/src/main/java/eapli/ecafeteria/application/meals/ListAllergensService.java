package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 * Created by k4rd050 on 27-04-2017.
 */
public class ListAllergensService {

        private final AllergenRepository allergenRepository = PersistenceContext.repositories().allergens();

        public Iterable<Allergen> allAllergens() {
            Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

            return this.allergenRepository.allergens();
        }

}
