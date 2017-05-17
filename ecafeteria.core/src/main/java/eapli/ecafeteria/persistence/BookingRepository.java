/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Date;

/**
 *
 * @author nunopinto
 */
public interface BookingRepository extends DataRepository<Booking, Long> {

    Iterable<Booking> findBookingByUserAndState(CafeteriaUser user, BookingState state);

    /**
     * It provides all bookings, whose meal happens within a specified number of
     * days, that are at one of the specified states and belongs to the
     * specified user.
     *
     * @param user The user to whom the Bookings belong.
     * @param states The states at which the bookings should be.
     * @param days The number of days (starting from the current day) in which
     * the booking's meal should occur.
     * @return It returns all matching bookings.
     */
    Iterable<Booking> findBookingByUserAndStatesAndWithinDays(CafeteriaUser user, Iterable<BookingState> states, int days);

    Booking findBookingByUserAndMealAndState(CafeteriaUser user, Meal meal, BookingState state);

    Iterable<Booking> checkBookingsByDateMealAndDishType(Date date, MealType mealType, DishType dishType);

    /**
     * It gets all the non evaluated bookings from the user.
     *
     * @param user The user to whom the Bookings belong.
     * @param state The state at which the bookings should be.
     * @return It returns all matching bookings.
     */
    Iterable<Booking> allNonEvaluatedBy(CafeteriaUser user, BookingState state);
}
