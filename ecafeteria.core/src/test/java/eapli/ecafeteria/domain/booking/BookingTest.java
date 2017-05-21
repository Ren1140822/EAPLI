/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Calendar;
import java.util.Currency;
import java.util.HashSet;
import static org.junit.Assert.assertTrue;

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
    private static SystemUser user;
    private static HashSet<RoleType> roles;
    private static CafeteriaUser cafeteriaUser;
    private static OrganicUnit organicUnit;
    private static MecanographicNumber mecanographicNumber;

    public BookingTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        mecanographicNumber = new MecanographicNumber("213123");
        organicUnit = new OrganicUnit("asfafsa", "afsa", "afsasfas");
        roles = new HashSet();
        roles.add(RoleType.ADMIN);
        user = new SystemUser("Teste", "Tesasf12t", "Adas", "asfas", "asfasfas@isep.ipp.pt", roles);
        cafeteriaUser = new CafeteriaUser(user, organicUnit, mecanographicNumber);
        dishType = new DishType("asdf", "fdsa");
        name = Designation.valueOf("qwer");
        price = new Money(20, Currency.getInstance("EUR"));
        dish = new Dish(dishType, name, price);
        mealType = new MealType(MealType.MealTypes.LUNCH);
        start = Calendar.getInstance();
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

    @Test(expected = IllegalStateException.class)
    public void ensureBookingHasUser() {
        new Booking(null, new Meal(dish, mealType, start));
    }

    @Test(expected = IllegalStateException.class)
    public void ensureBookingHasMeal() {
        new Booking(cafeteriaUser, null);
    }

    @Test
    public void ensureBookingIsCanceled() {
        Booking b = new Booking(cafeteriaUser, new Meal(dish, mealType, start));
        b.cancel();
        assertTrue(b.isAtState(BookingState.CANCELED));
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCanceledBookingsAreNotCanceled() {
        Booking b = new Booking(cafeteriaUser, new Meal(dish, mealType, start));
        b.cancel();
        b.cancel();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureDefinitiveBookingsAreNotCanceled() {
        Booking b = new Booking(cafeteriaUser, new Meal(dish, mealType, start));
        b.makeDefinitive();
        b.cancel();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureDeliveredBookingsAreNotCanceled() {
        Booking b = new Booking(cafeteriaUser, new Meal(dish, mealType, start));
        b.makeDefinitive();
        b.deliver();
        b.cancel();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureWastedBookingsAreNotCanceled() {
        Booking b = new Booking(cafeteriaUser, new Meal(dish, mealType, start));
        b.makeDefinitive();
        b.waste();
        b.cancel();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureDeliveredMealCannotBeRegisteredMoreThanOnceForSameClient() {
        Booking b = new Booking(cafeteriaUser, new Meal(dish, mealType, start));
        b.makeDefinitive();
        b.deliver();
        b.deliver();
    }

}
