/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.MealType.MealTypes;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Currency;
import java.util.logging.Logger;

/**
 * @author Meireles
 */
public class BookingBootstraper implements Action {

    @Override
    public boolean execute() {
        final Username username = new Username("900330");
        final CafeteriaUserRepository users = PersistenceContext.repositories().cafeteriaUsers(null);
        
        //FIXME
        // Use meals from bootstrap.
        //final MealRepository meals = PersistenceContext.repositories().meals();
        DishType dishType = new DishType("asdf", "fdsa");
        Designation name = Designation.valueOf("qwer");
        Money price = new Money(20, Currency.getInstance("EUR"));
        Dish dish = new Dish(dishType, name, price);
        MealType mealType = new MealType(MealTypes.ALMOCO);
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_MONTH, 5);
        TimePeriod2 timePeriod = new TimePeriod2(start, end);
        Meal meal = new Meal(dish,mealType,timePeriod);
        
        register(users.findByUsername(username),meal,BookingState.DONE);
        register(users.findByUsername(username),meal,BookingState.DONE);
        register(users.findByUsername(username),meal,BookingState.DELIVERED);
        register(users.findByUsername(username),meal,BookingState.DELIVERED);
        
        return false;
    }

    /**
     * It registers a booking.
     */
    private void register(CafeteriaUser user, Meal meal, BookingState actualState) {
        
        //FIXME
        // Use the controller to create a booking.
        
        final BookingRepository bookings = PersistenceContext.repositories().bookings();
        try {
            bookings.save(new Booking(user, meal, actualState));
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-BO001: bootstrapping existing record");
        }
    }

}
