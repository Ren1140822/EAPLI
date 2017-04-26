package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.Designation;
import org.junit.Test;

/**
 * Created by j040_ on 26/04/2017.
 */
public class AllergenTest {

    private String description = "wheat, rye, barley, oats or their hybridised strains, and products thereof";
    private final Designation cereals = Designation.valueOf("Cereals containing gluten");

    @Test(expected = IllegalStateException.class)
    public void testDescriptionMustNotBeNull() {
        System.out.println("must have a not null description");
        Allergen instance = new Allergen(cereals, null);
    }

    @Test(expected = IllegalStateException.class)
    public void testNameMustNotBeNull() {
        System.out.println("must have a name");
        Allergen instance = new Allergen(null, description);
    }
}