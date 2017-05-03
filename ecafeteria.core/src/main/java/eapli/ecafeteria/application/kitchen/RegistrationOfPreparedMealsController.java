/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealsPreparedRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.domain.range.TimePeriod;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Diogo Santos
 */
public class RegistrationOfPreparedMealsController {

    private final MealsPreparedRepository repository = PersistenceContext.repositories().mealsPrepared();

    public void findMeals(TimePeriod period, MealType mealType) {
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
        
    }

    public void insertQuantityOfPreparedMeals(Meal meal, int quantity) {

    }

}
