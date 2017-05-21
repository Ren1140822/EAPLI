/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.booking.CreateBookingController;
import eapli.ecafeteria.application.delivery.TopUpAccountCardController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.MealType.MealTypes;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Meireles
 */
public class BookingBootstraper implements Action {

    private final CreateBookingController theBookingController = new CreateBookingController();
    private final TopUpAccountCardController theTopUpController = new TopUpAccountCardController();

    @Override
    public boolean execute() {
        //FIXME
        //@author Meireles
        // Use meals from bootstrap.
        final DishRepository dishes = PersistenceContext.repositories().dishes();
        final Dish dish1 = dishes.findByName(Designation.valueOf("Chop Sausage"));
        final Dish dish2 = dishes.findByName(Designation.valueOf("Filet Steak"));
        final MealType lunch = new MealType(MealTypes.LUNCH);
        final MealType dinner = new MealType(MealTypes.DINNER);
        final Calendar t1 = Calendar.getInstance();
        final Calendar t2 = Calendar.getInstance();
        final Calendar t3 = Calendar.getInstance();
        final Calendar t4 = Calendar.getInstance();
        t1.add(Calendar.DATE, -1);
        t3.add(Calendar.DATE, 1);
        t4.add(Calendar.DATE, 6);
        //final MealRepository meals = PersistenceContext.repositories().meals();
        
        
        
        final Meal mealA = new Meal(dish1, lunch, t1);
        final Meal mealB = new Meal(dish2, dinner, t1);
        final Meal mealC = new Meal(dish1, lunch, t2);
        final Meal mealD = new Meal(dish2, dinner, t2);
        final Meal mealE = new Meal(dish2, lunch, t3);
        final Meal mealF = new Meal(dish1, dinner, t3);
        final Meal mealG = new Meal(dish1, lunch, t4);

        final CafeteriaUserRepository users = PersistenceContext.repositories().cafeteriaUsers(null);
        final CafeteriaUser user1 = users.findByUsername(new Username("900330"));
        final CafeteriaUser user2 = users.findByUsername(new Username("900331"));
         try {
            theTopUpController.insertCard(user1.mecanographicNumber().toString());
            theTopUpController.topUpCard(10000.0);
            theTopUpController.insertCard(user2.mecanographicNumber().toString());
            theTopUpController.topUpCard(10000.0);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(BookingBootstraper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        register(user1, mealA, BookingState.DELIVERED);
        register(user1, mealB, BookingState.DELIVERED);
        register(user1, mealC, BookingState.DEFINITIVE);
        register(user1, mealD, BookingState.DONE);
        register(user1, mealE, BookingState.CANCELED);
        register(user1, mealE, BookingState.DONE);
        register(user1, mealF, BookingState.DONE);
        register(user1, mealG, BookingState.DONE);
        
        register(user2, mealA, BookingState.DELIVERED);
        register(user2, mealB, BookingState.WASTED);
        register(user2, mealC, BookingState.DEFINITIVE);
        register(user2, mealD, BookingState.DONE);
        register(user2, mealE, BookingState.DONE);
        register(user2, mealF, BookingState.DONE);
        register(user2, mealF, BookingState.CANCELED);
        register(user2, mealG, BookingState.DONE);
        
        return false;
    }

    /**
     * It registers a booking at a certain state.
     */
    private void register(CafeteriaUser user, Meal meal, BookingState state) {
        final BookingRepository bookings = PersistenceContext.repositories().bookings(null);
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
