package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Allergen;

/**
 *
 * @author 1150623-GuilhermeFerreira
 */
public class ListAllergensController {

    private ListAllergensService svcAllergens = new ListAllergensService();

    public Iterable<Allergen> allergens() {
        return this.svcAllergens.allAllergens();
    }
}
