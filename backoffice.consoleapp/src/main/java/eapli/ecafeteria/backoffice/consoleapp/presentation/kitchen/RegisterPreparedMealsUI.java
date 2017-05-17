/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterPreparedMealsController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.MealPrinter;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt] Diogo Santos [1150451@isep.ipp.pt]
 */
public class RegisterPreparedMealsUI extends AbstractUI {

    private final RegisterPreparedMealsController controller = new RegisterPreparedMealsController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        Meal selectedMeal = null;
        int quantity = 0;
        final Iterable<Meal> meals = this.controller.findMeals();
        if (!meals.iterator().hasNext()) {
            System.out.println("There are no registered meals for the current day!");
        } else {
            final SelectWidget<Meal> selector = new SelectWidget<>("Meals:", meals, new MealPrinter());
            selector.show();
            selectedMeal = selector.selectedElement();
            if(selectedMeal == null){
                return false;
            }
            boolean parseSucess=false;
            while (!parseSucess){
            try {
                //FIXME
                //@Meireles
                // Check method "readInteger" from Console class.
            quantity = Integer.parseInt(Console.readLine("Quantity of Meals Prepared:"));
            parseSucess=true;
            } catch (NumberFormatException e){
                System.out.println("The quantity of meals wasn't on the right format.");
            }
            }
        }

        try {
            this.controller.registerQuantityOfPreparedMeals(selectedMeal, quantity);
            System.out.println("Registration suceeded.");
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("The quantity of prepared meals has already been introduced for this meal.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Registration of Quantity of Prepared Meals by Day/Type/Dish/Meal";
    }

}
