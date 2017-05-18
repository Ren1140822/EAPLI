/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.MealType.MealTypes;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class JpaBookingRepository extends JpaAutoTxRepository<Booking, Long>
        implements BookingRepository {

    public JpaBookingRepository(TransactionalContext autoTx) {
        super(Application.settings().getPersistenceUnitName(), autoTx);
    }

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
        StringBuilder query = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        query.append("e.user=:user");
        params.put("user", user);

        if (states.iterator().hasNext()) {
            query.append(" and ( ");
            short i = 0;
            for (BookingState state : states) {
                if (i == 0) {
                    query.append("e.state=:state");
                    params.put("state", state);
                } else {
                    String stateName = "state" + i;
                    query.append(" or e.state=:");
                    query.append(stateName);
                    params.put(stateName, state);
                }
                i++;
            }
            query.append(" ) ");
        }

        Calendar date = DateTime.now();
        params.put("date", new java.sql.Date(date.getTimeInMillis()));
        query.append(" and e.meal.date>=:date ORDER BY e.meal.date");

        Iterable<Booking> bookings = repo.match(query.toString(), params);
        Iterator<Booking> list = bookings.iterator();
        Booking next = null;
        if (list.hasNext()) {
            next = list.next();
            if (!next.meal().mealType().isOf(MealTypes.ALMOCO) && list.hasNext()) {
                Booking maybeNext = list.next();
                if (maybeNext.meal().getDate().equals(next.meal().getDate()) && maybeNext.meal().mealType().isOf(MealTypes.ALMOCO)) {
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
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("state", state);
        return repo.match("e.user=:user and e.state=:state", params);
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
        StringBuilder query = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        query.append("e.user=:user");
        params.put("user", user);

        if (states.iterator().hasNext()) {
            query.append(" and ( ");
            short i = 0;
            for (BookingState state : states) {
                if (i == 0) {
                    query.append("e.state=:state");
                    params.put("state", state);
                } else {
                    String stateName = "state" + i;
                    query.append(" or e.state=:");
                    query.append(stateName);
                    params.put(stateName, state);
                }
                i++;
            }
            query.append(" ) ");
        }

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, days);
        params.put("date", new java.sql.Date(date.getTimeInMillis()));
        query.append(" and e.meal.date <= :date");

        return repo.match(query.toString(), params);
    }

    @Override
    public Booking findBookingByUserAndMealAndState(CafeteriaUser user, Meal meal, BookingState state) {
        return repo.matchOne("e.user=:" + user + " and e.meal=:" + meal + " and e.state=:" + state);
    }

    @Override
    public Iterable<Booking> checkBookingsByDateMealAndDishType(Calendar date, MealType mealType, DishType dishType) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        params.put("mealType", mealType);
        params.put("dishType", dishType);
        return repo.match("e.meal.mealType=:mealType and e.meal.date=:date and e.meal.dish.dishType=:dishType", params);
    }

    /**
     * It provides all the non evaluated bookings by the respective user.
     *
     * @param user The user that requires the booking.
     * @param state The state that the booking has to be.
     * @return It returns a iterable with the bookings that aren't evaluated.
     */
    @Override
    public Iterable<Booking> allNonEvaluatedBy(CafeteriaUser user, BookingState state) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("state", state);
        return repo.match("e NOT IN (SELECT m.booking FROM MealEvaluation m) AND e.state = :state AND e.user =:user", params);
    }

    @Override
    public Iterable<Booking> findBookingsByDateAndMealTypeAndState(Calendar date, MealType mealType, BookingState state) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        params.put("mealType", mealType);
        params.put("state", state);
        return repo.match("e.meal.date=:date and e.meal.mealType=:mealType and e.state=:state", params);
    }

    public Booking findLatestBookingOfUserInDefinitiveState(CafeteriaUser user) {
        BookingState state = BookingState.DEFINITIVE;
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("state", state);

        return repo.matchOne("e.user=:user and e.state=:state ORDER BY e.meal.date DESC", params);
    }

}
