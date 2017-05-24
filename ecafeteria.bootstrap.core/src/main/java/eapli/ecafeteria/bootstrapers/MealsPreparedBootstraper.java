/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterPreparedMealsController;
import eapli.ecafeteria.domain.meals.*;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Santos
 */
public class MealsPreparedBootstraper implements Action {

    @Override
    public boolean execute() {
        final DishTypeRepository dishTypeRepo = PersistenceContext.repositories().dishTypes();
        final DishType vegie = dishTypeRepo.findByAcronym("vegie");
        final DishType fish = dishTypeRepo.findByAcronym("fish");
        final DishType meat = dishTypeRepo.findByAcronym("meat");
        final Dish dish1 = new Dish(vegie, Designation.valueOf("Summer Salad"),new NutricionalInfo(20,5), Money.euros(55.0));
        final Dish dish2 = new Dish(fish, Designation.valueOf("Cod Fish with Mashes Potatoes"),new NutricionalInfo(30,12), Money.euros(25.0));
        final Dish dish3 = new Dish(meat, Designation.valueOf("Francesinha"),new NutricionalInfo(40,12), Money.euros(16.0));
        final Dish dish4 = new Dish(fish, Designation.valueOf("Fish and Chips"),new NutricionalInfo(50,22), Money.euros(16.0));
        final Meal meal1 = new Meal(dish1, new MealType(MealType.MealTypes.LUNCH), Calendar.getInstance());
        final Meal meal2 = new Meal(dish3, new MealType(MealType.MealTypes.DINNER), Calendar.getInstance());
        final Meal meal3 = new Meal(dish4, new MealType(MealType.MealTypes.DINNER), Calendar.getInstance());
        register(meal1, 100);
        register(meal2, 150);
        register(meal3, 99);

        // For UC Preview Available Meals
        final Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DATE, -1);
        final DishRepository dishes = PersistenceContext.repositories().dishes();
        final Dish dish5 = dishes.findByName(Designation.valueOf("Chop Sausage"));
        final Meal meal4 = new Meal(dish5, new MealType(MealType.MealTypes.LUNCH), date1);
        final Dish dish6 = dishes.findByName(Designation.valueOf("Filet Steak"));
        final Meal meal5 = new Meal(dish6, new MealType(MealType.MealTypes.LUNCH), date1);
        final Dish dish7 = dishes.findByName(Designation.valueOf("Cod to the Brás"));
        final Meal meal6 = new Meal(dish7, new MealType(MealType.MealTypes.LUNCH), date1);
        final Dish dish8 = dishes.findByName(Designation.valueOf("Grilled Tofu"));
        final Meal meal7 = new Meal(dish8, new MealType(MealType.MealTypes.LUNCH), date1);

        register(meal4, 10);
        register(meal5, 10);
        register(meal6, 20);
        register(meal7, 5);

        return false;
    }

    private Calendar todaysCalendar(int dayShiftFromToday, int hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.add(Calendar.DAY_OF_MONTH, dayShiftFromToday);
        return calendar;
    }

    /**
     *
     */
    private void register(Meal meal, int quantitity) {
        final RegisterPreparedMealsController controller = new RegisterPreparedMealsController();
        try {
            controller.registerQuantityOfPreparedMeals(meal, quantitity);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
