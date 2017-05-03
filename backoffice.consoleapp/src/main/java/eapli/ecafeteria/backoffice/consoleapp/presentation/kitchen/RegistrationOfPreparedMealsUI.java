/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegistrationOfPreparedMealsController;
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
public class RegistrationOfPreparedMealsUI extends AbstractUI{
    
    private final RegistrationOfPreparedMealsController controller = new RegistrationOfPreparedMealsController();

    protected Controller controller(){
        return this.controller;
    }
    
    @Override
    protected boolean doShow() {
        final Iterable<Meal> meals = this.controller.findMeals();
        
        final SelectWidget<Meal> selector = new SelectWidget<>("Meals:", meals, new MealPrinter());
        selector.show();
        final Meal selectedMeal = selector.selectedElement();
        
        final int quantity = Integer.parseInt(Console.readLine("Quantity of Meals Prepared:"));
        
        try {
            this.controller.registerQuantityOfPreparedMeals(selectedMeal, quantity);
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
