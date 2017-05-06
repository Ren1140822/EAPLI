/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.domain.TimePeriod2;
import java.util.Calendar;
import java.util.Currency;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sofia Gonçalves [1150657@isep.ipp.pt] Pedro Chilro
 * [1150019@isep.ipp.pt]
 */
public class MealEvaluationTest {

    private static CafeteriaUser client;
    private static SystemUser user;
    private static OrganicUnit organicUnit;
    private static MecanographicNumber mecanographicNumber;
    private static HashSet<RoleType> roles;
    private static Dish dish;
    private static Calendar start;
    private static Calendar end;
    private static DishType dishType;
    private static Designation name;
    private static Money price;
    private static MealType mealType;
    private static TimePeriod2 timePeriod;
    private static Integer rate;

    public MealEvaluationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        mecanographicNumber = new MecanographicNumber("213123");
        organicUnit = new OrganicUnit("asfafsa", "afsa", "afsasfas");
        roles = new HashSet();
        roles.add(RoleType.CAFETERIA_USER);
        user = new SystemUser("Teste", "Tesasf12t", "Adas", "asfas", "asfasfas@isep.ipp.pt", roles);
        client = new CafeteriaUser(user, organicUnit, mecanographicNumber);
        dishType = new DishType("asdf", "fdsa");
        name = Designation.valueOf("qwer");
        price = new Money(20, Currency.getInstance("EUR"));
        dish = new Dish(dishType, name, price);
        start = Calendar.getInstance();
        end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_MONTH, 5);
        mealType = new MealType(MealType.MealTypes.ALMOCO);
        timePeriod = new TimePeriod2(start, end);
        rate = 2;
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
    public void ensureMealEvaluationHasBooking() {
        new MealEvaluation(null, new Rating(rate));
        new MealEvaluation(null, new Rating(rate), new Comment());
    }

    @Test(expected = IllegalStateException.class)
    public void ensureMealEvaluationHasRating() {
        Booking b = new Booking(client, new Meal(dish, mealType, Calendar.getInstance()));
        b.makeDefinitive();
        b.deliver();
        new MealEvaluation(b, null);
        new MealEvaluation(b, null, new Comment());
    }

    public void ensureMealEvaluationHasOptionalComment() {
        //maybe delete
    }

    public void ensureMealIsConsumedByClient() {
        //TO DO
    }

    

}
