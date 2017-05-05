package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.AllergenicInfo;
import eapli.framework.application.Controller;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by k4rd050 on 04-05-2017.
 */
public class RegisterDishAllergenController implements Controller {

    public AllergenicInfo registerDishAllergens(Set<Allergen> allergens) {
        return new AllergenicInfo(allergens);
    }
}
