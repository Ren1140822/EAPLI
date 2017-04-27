/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.domain.TimePeriod2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Calendar;
import java.util.Currency;

/**
 *
 * @author PC
 */
public class BookingTest {

    private static DishType dishType;
    private static Designation name;
    private static Money price;
    private static Dish dish;
    private static MealType mealType;
    private static Calendar start;
    private static Calendar end;
    private static TimePeriod2 timePeriod;

    public BookingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dishType = new DishType("asdf", "fdsa");
        name = Designation.valueOf("qwer");
        price = new Money(20, Currency.getInstance("€"));
        dish = new Dish(dishType, name, price);
        mealType = new MealType(MealType.MealTypes.ALMOCO);
        start = Calendar.getInstance();
        end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_MONTH, 5);
        timePeriod = new TimePeriod2(start, end);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test (expected=IllegalStateException.class)
    public void ensureBookingHasUser() {
        new Booking(null, new Meal(dish, mealType, timePeriod), BookingState.DONE);
    }

    @Test (expected=IllegalStateException.class)
    public void ensureBookingHasMeal() {
        new Booking(new CafeteriaUser(),null, BookingState.DONE);
    }
    
    @Test (expected=IllegalStateException.class)
    public void ensureBookingStateIsDone(){
        Booking b = new Booking(new CafeteriaUser(), new Meal(dish, mealType, timePeriod), BookingState.DELIVERED);
        b.cancel();
    }
    
    @Test
    public void ensureBookingIsCanceled(){
        Booking b = new Booking(new CafeteriaUser(), new Meal(dish, mealType, timePeriod), BookingState.DONE);
        b.cancel();
    }
    
}
