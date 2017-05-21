package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.MealType.MealTypes;
import eapli.util.DateTime;
import org.junit.Test;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class ShiftTest {

    @Test(expected = IllegalStateException.class)
    public void ensureDateCannotBeNull() {
        MealTypes mealTypes = MealTypes.LUNCH;
        MealType mealType = new MealType(mealTypes);
        new Shift(null, mealType);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureMealTypeCannotBeNull() {
        new Shift(DateTime.now(), null);
    }

}
