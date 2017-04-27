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
import eapli.ecafeteria.domain.meals.MenuEntry;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;

import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

import eapli.framework.domain.TimePeriod2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
        new Booking(null,new Meal(), BookingState.DONE);
    }
    @Test (expected=IllegalStateException.class)
    public void ensureBookingHasMeal() {
        new Booking(new CafeteriaUser(),null, BookingState.DONE);
    }
    
    @Test (expected=IllegalStateException.class)
    public void ensureBookingStateIsDone(){
        Booking b = new Booking(new CafeteriaUser(), new Meal(), BookingState.DELIVERED);
        b.cancel();
    }
    
    @Test
    public void ensureBookingIsCanceled(){
        Booking b = new Booking(new CafeteriaUser(), new Meal(), BookingState.DONE);
        b.cancel();
    }
    
}
