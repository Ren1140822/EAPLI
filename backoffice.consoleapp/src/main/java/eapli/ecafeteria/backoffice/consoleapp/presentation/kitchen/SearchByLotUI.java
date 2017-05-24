/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.SearchByLotController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.MealPrinter;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.util.io.Console;
import java.util.ArrayList;

/**
 *
 * @author João
 */
public class SearchByLotUI extends AbstractUI {

    private final SearchByLotController theController = new SearchByLotController();

    protected Controller controller() {
        return this.theController;
    }
    
    
    @Override
    protected boolean doShow() {
        String lotCode;
        Iterable<Meal> list = new ArrayList<>();
       
        lotCode = Console.readLine("Insert a lot code:");
        
        list = theController.showMealInMaterialsUsedByLot(lotCode);
        
        ListWidget<Meal> lister = new ListWidget<>("Meal", list, new MealPrinter());
        
        lister.show();
        
        
        Console.waitForKey("Press Enter to return.");
        return true;
        
    }

    @Override
    public String headline() {
        return "Search By Lot";
    }
    
}
