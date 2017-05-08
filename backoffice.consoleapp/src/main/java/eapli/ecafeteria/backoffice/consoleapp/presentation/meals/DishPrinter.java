/**
 *
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.AllergenicInfo;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.framework.visitor.Visitor;

import java.util.List;

/**
 * @author ajs 13/04/2016
 *
 */
class DishPrinter implements Visitor<Dish> {

    @Override
    public void visit(Dish visitee) {
        if (visitee.hasAllergens()) {
            System.out.printf("%-30s%-25s%-10s%-4s", visitee.name(), visitee.dishType().description(),
                    visitee.currentPrice(), String.valueOf(visitee.isActive()));
            AllergenicInfo allergenicInfo = visitee.allergens();
            List<Allergen> allergens = allergenicInfo.allergens();
            AllergenPrinter allergenPrinter = new AllergenPrinter();
            System.out.print(" - ");
            for(Allergen a : allergens){
                System.out.printf("%s , ",a.name().toString());
            }
            System.out.println();
        }else {
            System.out.printf("%-30s%-25s%-10s%-4s - No Allergens\n", visitee.name(), visitee.dishType().description(),
            visitee.currentPrice(), String.valueOf(visitee.isActive()));
        }
    }
}
