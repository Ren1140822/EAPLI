package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.RegisterDishController;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;

import java.util.Scanner;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class RegisterDishUI extends AbstractUI {

    private final RegisterDishController theController = new RegisterDishController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<DishType> dishTypes = this.theController.dishTypes();

        final SelectWidget<DishType> selector = new SelectWidget<>(dishTypes, new DishTypePrinter());
        selector.show();
        final DishType theDishType = selector.selectedElement();

        final String name = Console.readLine("Name");

        final NutricionalInfoDataWidget nutricionalData = new NutricionalInfoDataWidget();

        nutricionalData.show();

        final double price = Console.readDouble("Price");

        try {
            this.theController.registerDish(theDishType, name, nutricionalData.calories(), nutricionalData.salt(),
                    price);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter a dish which already exists in the database.");
        }
        /*/
        boolean hasAllergens = askForAllergens();
        if(hasAllergens){
            final Iterable<Allergen>allergens = this.theController.allergens();

        }
        /*/
        return false;
    }

    /*/private boolean askForAllergens(){
        Scanner in = new Scanner(System.in);
        System.out.println("Does this dish have allergens? (Y/N)\n");
        do {
            String op = in.next();
            switch(op){
                case "Y":
                    return true;
                    break;
                case "N":
                    return false;
                    break;
                default:
                    System.out.println("Invalid Input, answer with 'Y' for yes or 'N' for no\n");
                    break;
            }

        }while(true);
        final MultipleSelectWidget<DishType> selector = new MultipleSelectWidget<>(dishTypes, new DishTypePrinter());
        selector.show();
        final DishType theDishType = selector.selectedElement();
    }/*/

    @Override
    public String headline() {
        return "Register Dish";
    }
}
