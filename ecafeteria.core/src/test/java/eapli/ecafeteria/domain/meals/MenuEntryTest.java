package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.domain.TimePeriod2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Currency;

/**
 * Created by pyska on 27-04-2017.
 */
public class MenuEntryTest {

    private Dish dish;
    private MealType mealType;
    private TimePeriod2 timePeriod;
    private DishType dishType;
    private Designation name;
    private Money price;

    public MenuEntryTest(){}

    @Before
    public void setUp(){
        dishType = new DishType("fdsa", "description");
        name = Designation.valueOf("qwerty");
        price = new Money(10, Currency.getInstance("€"));
        dish = new Dish(dishType, name, price);
        mealType = new MealType("asdf");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_MONTH, 5);
        timePeriod = new TimePeriod2(start, end);
    }

    @Test(expected = IllegalStateException.class)
    public void testDishMustNotBeNull(){
        System.out.println("MenuEntry: dish must not be null");
        MenuEntry instance = new MenuEntry(null, mealType, timePeriod);
    }

    @Test(expected = IllegalStateException.class)
    public void testMealTypeMustNotBeNull(){
        System.out.println("MenuEntry: meal type must not be null");
        MenuEntry instance = new MenuEntry(dish, null, timePeriod);
    }

    @Test(expected = IllegalStateException.class)
    public void testTimePeriodMustNotBeNull(){
        System.out.println("MenuEntry: time period must not be null");
        MenuEntry instance = new MenuEntry(dish, mealType, null);
    }
}
