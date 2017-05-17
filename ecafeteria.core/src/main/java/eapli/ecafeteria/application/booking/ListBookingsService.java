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
     
    public Iterable<Booking> listBookingsByDateMealAndDishType(Date date, MealType mealType, DishType dishType){
        return this.repo.checkBookingsByDateMealAndDishType(date, mealType, dishType);
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

}
