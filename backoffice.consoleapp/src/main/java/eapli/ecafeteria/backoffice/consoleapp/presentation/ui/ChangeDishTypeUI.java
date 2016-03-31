/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.application.ChangeDishTypeController;
import eapli.ecafeteria.domain.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Nuno
 */
public class ChangeDishTypeUI extends AbstractUI{
    
    private final ChangeDishTypeController theController = new ChangeDishTypeController();

    @Override
    protected Controller controller() {
        return theController;
    }

    @Override
    protected boolean doShow() {
//         final String acronym = Console.readLine("Dish Type Acronym:");
//         final String description = Console.readLine("Dish Type Description:");
           DishType updtDishType = new DishType("testeACR", "testeDESCR");
           theController.changeDishType(updtDishType);
        return true;
    }

    @Override
    public String headline() {
        return "Change Dish Type description";  
    }
    
}
