package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishAllergen;
import eapli.framework.application.Controller;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by k4rd050 on 04-05-2017.
 */
public class RegisterDishAllergenController implements Controller {

    public Set<DishAllergen> registerDishAllergens(Set<Allergen> allergens, Dish dish) {
        Set<DishAllergen>dishAllergens = new HashSet<>();
        for(Allergen a : allergens){
            DishAllergen d = new DishAllergen(dish, a);
            dishAllergens.add(d);
        }
        return dishAllergens;
    }
}
