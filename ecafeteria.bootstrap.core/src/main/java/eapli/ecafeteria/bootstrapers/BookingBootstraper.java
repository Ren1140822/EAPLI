/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.booking.CreateBookingController;
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
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
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

    private final CreateBookingController theBookingController = new CreateBookingController();

    @Override
    public boolean execute() {
        //FIXME
        //@author Meireles
        // Use meals from bootstrap.
        final DishRepository dishes = PersistenceContext.repositories().dishes();
        final Dish dish1 = dishes.findByName(Designation.valueOf("costeleta à salsicheiro"));
        final Dish dish2 = dishes.findByName(Designation.valueOf("picanha"));
        final MealType lunch = new MealType(MealTypes.ALMOCO);
        final MealType dinner = new MealType(MealTypes.ALMOCO);
        final Calendar start = Calendar.getInstance();
        final Meal mealA = new Meal(dish1, lunch, start);
        final Meal mealB = new Meal(dish2, dinner, start);
        //final MealRepository meals = PersistenceContext.repositories().meals();

        final Meal meal1 = mealA;
        final Meal meal2 = mealB;
        final Meal meal3 = mealA;
        final Meal meal4 = mealB;
        final Meal meal5 = mealA;
        final Meal meal6 = mealA;
        final Meal meal7 = mealA;
        final Meal meal8 = mealB;
        final Meal meal9 = mealA;
        final Meal meal10 = mealB;

        final CafeteriaUserRepository users = PersistenceContext.repositories().cafeteriaUsers(null);
        final CafeteriaUser user1 = users.findByUsername(new Username("900330"));
        final CafeteriaUser user2 = users.findByUsername(new Username("900331"));

        register(user1, meal1, BookingState.DONE);
        register(user1, meal2, BookingState.DONE);
        register(user1, meal3, BookingState.CANCELED);
        register(user1, meal4, BookingState.CANCELED);
        register(user1, meal5, BookingState.DEFINITIVE);
        register(user1, meal6, BookingState.DEFINITIVE);
        register(user1, meal7, BookingState.DELIVERED);
        register(user1, meal8, BookingState.DELIVERED);
        register(user1, meal9, BookingState.WASTED);
        register(user1, meal10, BookingState.WASTED);

        register(user2, meal1, BookingState.DONE);
        register(user2, meal2, BookingState.DONE);
        register(user2, meal3, BookingState.CANCELED);
        register(user2, meal4, BookingState.CANCELED);
        register(user2, meal5, BookingState.DEFINITIVE);
        register(user2, meal6, BookingState.DEFINITIVE);
        register(user2, meal7, BookingState.DELIVERED);
        register(user2, meal8, BookingState.DELIVERED);
        register(user2, meal9, BookingState.WASTED);
        register(user2, meal10, BookingState.WASTED);

        return false;
    }

    /**
     * It registers a booking at a certain state.
     */
    private void register(CafeteriaUser user, Meal meal, BookingState state) {
        final BookingRepository bookings = PersistenceContext.repositories().bookings();
        try {
            Booking b = theBookingController.registerBooking(user, meal);
            changeToState(b, state);
            bookings.save(b);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-BO001: bootstrapping existing record");
        }
    }

    /**
     * It changes a booking to the wanted state.
     *
     * @param booking The booking to be changed.
     * @param wanted The wanted BookingState.
     * @return It returns the booking at the wanted state.
     */
    private Booking changeToState(Booking booking, BookingState wanted) {
        switch (wanted) {
            case CANCELED:
                booking.cancel();
                break;
            case DEFINITIVE:
                booking.makeDefinitive();
                break;
            case WASTED:
                booking.makeDefinitive();
                booking.waste();
                break;
            case DELIVERED:
                booking.makeDefinitive();
                booking.deliver();
                break;
            default:
                break;
        }
        return booking;
    }

}
