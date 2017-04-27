package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 * Created by pyska on 26-04-2017.
 */
public class ListMealTypeService {
    private MealTypeRepository mealTypeRepository = PersistenceContext.repositories().mealTypes();

    public Iterable<MealType> allMealTypes() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.mealTypeRepository.findAll();
    }
}
