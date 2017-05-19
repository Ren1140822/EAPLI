package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.cafeteria.ListOrganicUnitsController;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.meals.*;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.ecafeteria.domain.authz.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pyska on 26-04-2017.
 */
public class RegisterMenuController implements Controller {
    
    private final MenuRepository repository = PersistenceContext.repositories().menus();
    private final ListDishService dishService = new ListDishService();
    private final ListMealTypeService mealTypeService = new ListMealTypeService();
    private final ListOrganicUnitsService organicUnitsService = new ListOrganicUnitsService();

    public Menu registerMenu(Set<Meal> meals, OrganicUnit organicUnit, TimePeriod2 period)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final Menu newMenu = new Menu(period, organicUnit);
        newMenu.addAllMeals(meals);

        Menu menu = repository.save(newMenu);
        return menu;
    }

    public Iterable<Dish> allDishes(){
        return dishService.allDishes();
    }

    public Iterable<MealType.MealTypes> allMealTypes(){
        return mealTypeService.allMealTypes();
    }

    public Iterable<OrganicUnit> allOrganicUnits() {
        return organicUnitsService.listOrganicUnits();
    }
}
