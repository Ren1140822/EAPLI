/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Date;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class InMemoryBookingRepository extends InMemoryRepositoryWithLongPK<Booking>
        implements BookingRepository {

    @Override
    public Iterable<Booking> findBookingByUserAndState(CafeteriaUser user, BookingState state) {
        return match(e -> e.belongsTo(user) && e.isAtState(state));
    }

    @Override
    public Booking findBookingByUserAndMealAndState(CafeteriaUser user, Meal meal, BookingState state) {
        return matchOne(e -> e.belongsTo(user) &&e.isOfMeal(meal) && e.isAtState(state));
    }

    @Override
    public Iterable<Booking> checkBookingsByDateMealAndDishType(Date date, MealType mealType, DishType dishType) {
        //TODO: Implement the method
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
