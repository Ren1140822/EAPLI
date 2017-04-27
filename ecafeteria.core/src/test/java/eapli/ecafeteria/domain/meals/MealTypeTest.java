package eapli.ecafeteria.domain.meals;

import eapli.util.Strings;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pyska on 27-04-2017.
 */
public class MealTypeTest {

    public MealTypeTest(){}

    @Test(expected = IllegalStateException.class)
    public void testMealTypeMustNotBeNull(){
        System.out.println("MealTypeTest: string must not be null");
        MealType instance = new MealType(null);
    }
}
