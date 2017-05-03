/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterMaterialController;
import eapli.ecafeteria.application.kitchen.RegistrationOfPreparedMealsController;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.domain.TimePeriod2;
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
        final Dish dish1 = new Dish(vegie, Designation.valueOf("Summer Salad"), Money.euros(55.0));
        final Dish dish2 = new Dish(fish, Designation.valueOf("Cod Fish with Mashes Potatoes"), Money.euros(25.0));
        final Dish dish3 = new Dish(meat, Designation.valueOf("Francesinha"), Money.euros(16.0));
        final Dish dish4 = new Dish(fish, Designation.valueOf("Fish and Chips"), Money.euros(16.0));
        final Meal meal1 = new Meal(dish1, new MealType(MealType.MealTypes.ALMOCO), new TimePeriod2(todaysCalendar(0, 10), todaysCalendar(0, 14)));
        final Meal meal2 = new Meal(dish3, new MealType(MealType.MealTypes.JANTAR), new TimePeriod2(todaysCalendar(0, 10), todaysCalendar(0, 14)));
        register(meal1, 100);
        register(meal2, 150);
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
        final RegistrationOfPreparedMealsController controller = new RegistrationOfPreparedMealsController();
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
