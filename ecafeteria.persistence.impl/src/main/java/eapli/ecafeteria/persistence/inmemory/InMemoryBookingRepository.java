/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class InMemoryBookingRepository extends InMemoryRepositoryWithLongPK<Booking>
        implements BookingRepository {

    /**
     * It finds the next booking from the user which is at any of the given
     * states.
     *
     * @param user The user who owns the booking.
     * @param states The states in which the booking might be.
     * @return It returns the next booking or null if none was found.
     */
    @Override
    public Booking findNextBookingOfUserAtState(CafeteriaUser user, Iterable<BookingState> states) {
        Iterable<Booking> bookings = match(e -> e.belongsTo(user) && isBookingAtOneOfTheStates(states, e) && (DateTime.isTodayOnwards(e.meal().getDate())));
        Iterator<Booking> list = bookings.iterator();
        Booking next = null;
        if (list.hasNext()) {
            next = list.next();
            if (!next.meal().mealType().isOf(MealType.MealTypes.LUNCH) && list.hasNext()) {
                Booking maybeNext = list.next();
                if (maybeNext.meal().getDate().equals(next.meal().getDate()) && maybeNext.meal().mealType().isOf(MealType.MealTypes.LUNCH)) {
                    next = maybeNext;
                }
            }
        }
        return next;
    }

    /**
     * It finds the bookings of a given Cafeteria User that are at a given
     * state.
     *
     * @param user The Cafeteria User that owns the booking.
     * @param state The state of the bookings to search for.
     * @return
     */
    @Override
    public Iterable<Booking> findBookingByUserAndState(CafeteriaUser user, BookingState state) {
        return match(e -> e.belongsTo(user) && e.isAtState(state));
    }

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
    @Override
    public Iterable<Booking> findBookingByUserAndStatesAndWithinDays(CafeteriaUser user, Iterable<BookingState> states, int days) {
        Calendar limitDate = Calendar.getInstance();
        limitDate.add(Calendar.DAY_OF_MONTH, days);
        return match(e -> e.belongsTo(user) && isBookingAtOneOfTheStates(states, e) && e.isUntilDate(limitDate));
    }

    @Override
    public Booking findBookingByUserAndMealAndState(CafeteriaUser user, Meal meal, BookingState state) {
        return matchOne(e -> e.belongsTo(user) && e.isOfMeal(meal) && e.isAtState(state));
    }

    @Override
    public Iterable<Booking> checkBookingsByDateMealAndDishType(Calendar date, Iterable<MealType> mealType, DishType dishType) {
        return match(e -> e.isSameDate(date) && bookingMealTypes(mealType, e) && e.meal().dish().dishType().sameAs(dishType));
    }

    @Override
    public Iterable<Booking> allNonEvaluatedBy(CafeteriaUser user, BookingState state) {
        return match(e -> e.belongsTo(user) && e.isAtState(state));
    }

    /**
     * It checks if the booking is at one of the states of the list.
     *
     * @param states The list with the states.
     * @param booking THe booking to be examined.
     * @return It returns "true" if the booking is at one of the states within
     * the list or "false" otherwise.
     */
    private boolean isBookingAtOneOfTheStates(Iterable<BookingState> states, Booking booking) {
        for (BookingState state : states) {
            if (booking.isAtState(state)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean bookingMealTypes(Iterable<MealType> mealTypes, Booking booking) {
        for (MealType mealT : mealTypes) {
            if (booking.meal().mealType().isOf(MealType.MealTypes.valueOf(mealT.mealType()))) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Iterable<Booking> findBookingsByDateAndMealTypeAndState(Calendar date, MealType mealType, BookingState state) {
        return match(e -> e.meal().getDate().compareTo(date) == 0 && e.meal().mealType().isOf(MealType.MealTypes.valueOf(mealType.mealType())) && e.isAtState(state));
    }

    public Booking findLatestBookingOfUserInDefinitiveState(CafeteriaUser user) {
        Iterable<Booking> bookings = match(e -> e.belongsTo(user) && e.isAtState(BookingState.DEFINITIVE));
        Comparator<Booking> comp = new Comparator<Booking>() {
            @Override
            public int compare(Booking t, Booking t1) {
                return t.compareDate(t1) ? 1 : -1;
            }
        };
        LinkedList<Booking> bookingsList = new LinkedList();
        bookings.forEach(bookingsList::add);
        Collections.sort(bookingsList, comp);
        return bookingsList.getLast();
    }

}
