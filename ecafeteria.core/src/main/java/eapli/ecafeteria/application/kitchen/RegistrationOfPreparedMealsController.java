/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealsPreparedRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.domain.range.TimePeriod;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Diogo Santos
 */
public class RegistrationOfPreparedMealsController {

    private final MealsPreparedRepository repository = PersistenceContext.repositories().mealsPrepared();
    private final ListMealService listMealsSvc = new ListMealService();

    public Iterable<Meal> findMeals(TimePeriod period, MealType mealType) {
        // today    
        Calendar date = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        Calendar dateMidnight = new GregorianCalendar();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        // next day
        date.add(Calendar.DAY_OF_MONTH, 1);

        TimePeriod2 timePeriod2 = new TimePeriod2(date, dateMidnight);
        return this.listMealsSvc.listMealsByDate(timePeriod2);
    }

    public MealsPrepared registerQuantityOfPreparedMeals(Meal meal, int quantity) throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        final MealsPrepared mealsPrepared = new MealsPrepared(meal, quantity);
        return repository.save(mealsPrepared);

    }

}
