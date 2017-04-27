package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * Created by pyska on 26-04-2017.
 */
public class RegisterMenuController implements Controller {
    private final MenuRepository repository = PersistenceContext.repositories().menus();
    private final ListDishService dishService = new ListDishService();
    private final ListMealTypeService mealTypeService = new ListMealTypeService();

    public Menu registerMenu(Dish dish, MealType mealType, TimePeriod2 timePeriod)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final Menu newMenu = new Menu(new Meal(dish, mealType, timePeriod));
        Menu menu = repository.save(newMenu);
        return menu;
    }

    public Iterable<Dish> allDishes(){
        return dishService.allDishes();
    }

    public Iterable<MealType.MealTypes> allMealTypes(){
        return mealTypeService.allMealTypes();
    }
}
