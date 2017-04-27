/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

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

import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class BookingTest {
    
    public BookingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
        DishType dishType = new DishType("asdf", "fdsa");
        Designation name = Designation.valueOf("qwer");
        Money price = new Money(20, Currency.getInstance("€"));
        Dish dish = new Dish(dishType, name, price);
        MealType mealType = new MealType(MealType.MealTypes.ALMOCO);
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_MONTH, 5);
        TimePeriod2 timePeriod = new TimePeriod2(start, end);
        new Booking(null, new Meal(dish, mealType, timePeriod));
    }
    @Test (expected=IllegalStateException.class)
    public void ensureBookingHasMeal() {
        new Booking(new CafeteriaUser(),null);
    }
    
}
