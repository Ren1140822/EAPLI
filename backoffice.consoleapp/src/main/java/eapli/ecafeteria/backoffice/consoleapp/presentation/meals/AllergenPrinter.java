package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.framework.visitor.Visitor;

/**
 * Created by k4rd050 on 03-05-2017.
 */
public class AllergenPrinter implements Visitor<Allergen> {
    @Override
    public void visit(Allergen visitee) {
        System.out.printf("%s\n",visitee.name().toString());
    }
}
