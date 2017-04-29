package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.domain.TimePeriod2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pyska on 27-04-2017.
 */
public class MenuTest {

    private Meal meal;
    private Dish dish;
    private MealType mealType;
    private TimePeriod2 timePeriod;
    private DishType dishType;
    private Designation name;
    private Money price;
    private SystemUser systemUser;

    public MenuTest() {
    }

    @Before
    public void setUp() {
        dishType = new DishType("fdsa", "description");
        name = Designation.valueOf("qwerty");
        price = new Money(10, Currency.getInstance("EUR"));
        dish = new Dish(dishType, name, price);
        mealType = new MealType(MealType.MealTypes.ALMOCO);
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_MONTH, 5);
        timePeriod = new TimePeriod2(start, end);
        meal = new Meal(dish, mealType, timePeriod);
        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.ADMIN);
        roles.add(RoleType.MENU_MANAGER);
        roles.add(RoleType.KITCHEN_MANAGER);
        systemUser= new SystemUser("poweruser", "poweruserA1", "joe", "doe", "joe@email.org", roles);

    }

    @Test(expected = IllegalStateException.class)
    public void testMenuEntryMustNotBeNull() {
        System.out.println("MenuTest: must not have a null menu entry");
        Menu instance = new Menu(null,null);
    }

    @Test
    public void testIsPublished() {
        System.out.println("MenuTest: testing 'isPublished' method normal behaviour");
        Menu instance = new Menu(meal,systemUser);
        boolean result = instance.isPublished();
        Assert.assertEquals(true, result);
    }
}
