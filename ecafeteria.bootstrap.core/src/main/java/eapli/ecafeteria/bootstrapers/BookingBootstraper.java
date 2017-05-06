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
        //@author Meireles
        // Use meals from bootstrap.
        //final MealRepository meals = PersistenceContext.repositories().meals();
        DishType dishType = new DishType("Special Type", "Bootstrap special type. Please delete me.");
        Designation name = Designation.valueOf("Bootstrapalicious");
        Money price = new Money(20, Currency.getInstance("EUR"));
        Dish dish = new Dish(dishType, name, price);
        MealType mealType = new MealType(MealTypes.ALMOCO);
        Calendar start = Calendar.getInstance();
        Meal meal = new Meal(dish, mealType, start);

        register(users.findByUsername(username), meal, BookingState.DONE);
        register(users.findByUsername(username), meal, BookingState.DONE);
        register(users.findByUsername(username), meal, BookingState.DELIVERED);
        register(users.findByUsername(username), meal, BookingState.DELIVERED);

        return false;
    }

    /**
     * It registers a booking.
     */
    private void register(CafeteriaUser user, Meal meal, BookingState actualState) {

        //FIXME
        //@author Meireles
        // Use the controller to create a booking.
        final BookingRepository bookings = PersistenceContext.repositories().bookings();
        try {
            Booking b = new Booking(user, meal);
            if (!BookingState.DONE.equals(actualState)) {
                if (BookingState.CANCELED.equals(actualState)) {
                    b.cancel();
                } else {
                    b.makeDefinitive();
                }
                if (!BookingState.DEFINITIVE.equals(actualState)) {
                    if (BookingState.WASTED.equals(actualState)) {
                        b.waste();
                    } else {
                        b.deliver();
                    }
                }
            }
            bookings.save(b);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-BO001: bootstrapping existing record");
        }
    }

}
