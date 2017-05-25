/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class ListBookingsService {

    private final BookingRepository repo = PersistenceContext.repositories().bookings(null);

    /**
     * It lists all bookings that are currently at the state "Done" and are
     * owned by a certain CafeteriaUser.
     *
     * @param user The owner of the bookings to be searched for.
     * @return It returns an iterable with all the matching bookings.
     */
    public Iterable<Booking> findBookingsStateDoneOf(CafeteriaUser user) {
        return this.repo.findBookingByUserAndState(user, BookingState.DONE);
    }

    public Iterable<Booking> findBookingsStateDeliveredOf(CafeteriaUser user) {
        return this.repo.findBookingByUserAndState(user, BookingState.DELIVERED);
    }

    public Iterable<Booking> findBookingsStateDefinitiveOf(CafeteriaUser user) {
        return this.repo.findBookingByUserAndState(user, BookingState.DEFINITIVE);
    }
    
     /**
     * Lists all existing bookings at a given date, with a certain meal type and dish type
     
     * @param date date for search
     * @param mealType meal type for search
     * @param dishType dish type for search
     * 
     * @return Returns an iterable with all the bookings fitting the parameters
     */
    public Iterable<Booking> listBookingsByDateMealAndDishType(Calendar date, String mealType, DishType dishType) {
                ArrayList<MealType> mealTypes = new ArrayList<>();
        
        final MealType.MealTypes mealTypee=null;
        
        if(mealType.equalsIgnoreCase("Lunch")){
            mealTypes.add(new MealType(mealTypee.LUNCH));
            
            return this.repo.checkBookingsByDateMealAndDishType(date, mealTypes , dishType);
        } 
        else if (mealType.equalsIgnoreCase("Dinner")){
            mealTypes.add(new MealType(mealTypee.DINNER));
            
            return this.repo.checkBookingsByDateMealAndDishType(date, mealTypes, dishType);
        }
        
        mealTypes.add(new MealType(mealTypee.LUNCH));
        mealTypes.add(new MealType(mealTypee.DINNER));
        
        return this.repo.checkBookingsByDateMealAndDishType(date, mealTypes, dishType);
    }

    /**
     * It lists all bookings that are currently at the state "Done" or
     * "Definitive", whose meal happens within a specified number of days and
     * are owned by a certain CafeteriaUser.
     *
     * @param user The owner of the bookings to be searched for.
     * @param days The number of days within the bookings' meal should occur.
     * @return It returns an iterable with all the matching bookings.
     */
    public Iterable<Booking> findActiveBookingsFromNextDaysOf(CafeteriaUser user, int days) {
        List<BookingState> states = new ArrayList<>();
        states.add(BookingState.DONE);
        states.add(BookingState.DEFINITIVE);
        return this.repo.findBookingByUserAndStatesAndWithinDays(user, states, days);
    }

    /**
     * It lists all bookings that are currently at the state "Done" or
     * "Definitive", whose meal happens within a specified number of days and
     * are owned by a certain CafeteriaUser.
     *
     * @param date
     * @param mealType
     * @param state
     * @return It returns an iterable with all the matching bookings.
     */
    public Iterable<Booking> findBookingsByDateAndMealTypeAndState(Calendar date, MealType mealType, BookingState state) {
        return this.repo.findBookingsByDateAndMealTypeAndState(date, mealType, state);
    }

    /**
     * It finds the next booking at state "Done" or "Definitive" from the user.
     *
     * @param user The user who owns the bookings.
     * @return It returns the next booking or null if none was found.
     */
    public Booking findNextActiveBookingOf(CafeteriaUser user) {
        List<BookingState> states = new ArrayList<>();
        states.add(BookingState.DONE);
        states.add(BookingState.DEFINITIVE);
        return this.repo.findNextBookingOfUserAtState(user, states);
    }

    /**
     * Finds the latest booking of the user that is in definitive state.
     *
     * @param user the owner of the booking
     * @return the booking or null if not found
     */
    public Booking findLatestBookingOfUserInDefinitiveState(CafeteriaUser user) {
        return this.repo.findLatestBookingOfUserInDefinitiveState(user);
    }

}
