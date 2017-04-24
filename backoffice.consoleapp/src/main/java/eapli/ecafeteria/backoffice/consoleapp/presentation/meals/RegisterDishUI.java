package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.RegisterDishController;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;

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

        return false;
    }

    @Override
    public String headline() {
        return "Register Dish";
    }
}
