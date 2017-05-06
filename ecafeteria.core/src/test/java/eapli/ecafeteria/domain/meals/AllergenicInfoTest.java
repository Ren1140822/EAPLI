package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.Designation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by k4rd050 on 05-05-2017.
 */
public class AllergenicInfoTest {

    private List<Allergen> set1 = new ArrayList<>();

    @Test(expected = IllegalStateException.class)
    public void testAllergenListMustNotBeNull() {
        System.out.println("must have a not null set of allergens");
        AllergenicInfo instance = new AllergenicInfo(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testAllergenListMustNotBeEmpty() {
        System.out.println("must have at least one allergen");
        AllergenicInfo instance = new AllergenicInfo(set1);
    }
}
