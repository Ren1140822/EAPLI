package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * Created by k4rd050 on 03-05-2017.
 */
public class ListAllergenUI extends AbstractListUI {

    AllergenRepository rep = PersistenceContext.repositories().allergens();

    @Override
    protected Iterable<Allergen> listOfElements() {
        return rep.allergens();
    }

    @Override
    protected Visitor<Allergen> elementPrinter() {
        return new AllergenPrinter();
    }

    @Override
    protected String elementName() {
        return "Allergen";
    }
}
