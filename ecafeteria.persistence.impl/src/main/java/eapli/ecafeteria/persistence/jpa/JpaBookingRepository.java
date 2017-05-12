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
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
                i ++;
            }
            query.append(" ) ");
        }

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, days);

        query.append(" and e.meal.date <= '");
        query.append(date.get(Calendar.YEAR));
        query.append("-");
        query.append(date.get(Calendar.MONTH));
        query.append("-");
        query.append(date.get(Calendar.DAY_OF_MONTH));
        query.append("'");

        return repo.match(query.toString(), params);
    }

    @Override
    public Booking findBookingByUserAndMealAndState(CafeteriaUser user, Meal meal, BookingState state) {
        return repo.matchOne("e.user=:" + user + " and e.meal=:" + meal + " and e.state=:" + state);
    }

    @Override
    public Iterable<Booking> checkBookingsByDateMealAndDishType(Date date, MealType mealType, DishType dishType) {
       Map<String, Object> params = new HashMap<>();
        params.put("date",date);
        params.put("mealType",mealType);
        params.put("dishType",dishType);
        return repo.match("e.meal.mealType=:mealType and e.meal.date=:date and e.meal.dish.dishType=:dishType", params);
    }

    @Override
    public Iterable<Booking> allNonEvaluatedBy(CafeteriaUser user) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        return repo.match("e NOT IN (SELECT m.booking FROM MealEvaluation m WHERE m.booking.user = :user)", params);
    }

}
