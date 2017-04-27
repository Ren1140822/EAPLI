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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


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
