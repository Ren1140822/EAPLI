/**
 *
 */
package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.ecafeteria.domain.meals.Dish;
import eapli.framework.visitor.Visitor;


/**
 * @author ajs 13/04/2016
 *
 */
public class DishPrinter implements Visitor<Dish> {

    @Override
    public void visit(Dish visitee) {
        System.out.printf("%-25s%-25s%-10s\n", visitee.name(), visitee.dishType().description(),
                visitee.currentPrice());
    }
}
