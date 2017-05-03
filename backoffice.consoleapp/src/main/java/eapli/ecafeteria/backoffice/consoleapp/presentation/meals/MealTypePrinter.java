/**
 *
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.visitor.Visitor;

/**
 * @author Paulo Gandra Sousa
 *
 */
class MealTypePrinter implements Visitor<MealType.MealTypes> {

    @Override
    public void visit(MealType.MealTypes visitee) {
        System.out.printf("%-10s\n", visitee.toString());
    }

    @Override
    public void beforeVisiting(MealType.MealTypes visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(MealType.MealTypes visitee) {
        // nothing to do
    }
}
