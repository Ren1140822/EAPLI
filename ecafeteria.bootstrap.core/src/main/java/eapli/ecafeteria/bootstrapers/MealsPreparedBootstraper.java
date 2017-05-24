package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterPreparedMealsController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Santos
 */
public class MealsPreparedBootstraper implements Action {

    @Override
    public boolean execute() {
        final Calendar yesterday = DateTime.yesterday();
        final Calendar today = DateTime.now();

        final MealType lunch = new MealType(MealType.MealTypes.LUNCH);
        final MealType dinner = new MealType(MealType.MealTypes.DINNER);
        final MealRepository meals = PersistenceContext.repositories().meals();

        Iterator<Meal> list = meals.findByDateAndMealType(yesterday, dinner).iterator();
        final Meal yesterdayDinnerA = list.next();
        final Meal yesterdayDinnerB = list.next();

        list = meals.findByDateAndMealType(today, lunch).iterator();
        final Meal todayLunchA = list.next();
        final Meal todayLunchB = list.next();

        register(yesterdayDinnerA, 100);
        register(yesterdayDinnerB, 150);
        register(todayLunchA, 99);
        register(todayLunchB, 78);
        return false;
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
