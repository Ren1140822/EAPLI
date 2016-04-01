/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.application.ChangeDishTypeController;
import eapli.ecafeteria.application.ListDishTypeController;
import eapli.ecafeteria.domain.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;
import java.util.Iterator;

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
           Boolean res;
           int dishTypeKey;
           int n;
           String newDescription;
           DishType updtDishType = new DishType(null, null);
           Iterator<DishType> dishTypeIterator;
           
           Iterable<DishType> allDishTypes = new ListDishTypeController().listDishTypes();
           dishTypeIterator = allDishTypes.iterator();
           res = new ListDishTypeUI().doShowIterable(allDishTypes);
           
           dishTypeKey = Console.readInteger("Select dish type to change");
           n = 0;
           //iterators do not implement random access, sequential access required to reach the object selected by user
           while (dishTypeIterator.hasNext() && n != dishTypeKey) {
                updtDishType = dishTypeIterator.next();
                n++;
           }

           if (dishTypeKey == n) { //DishType selected by user exists
               newDescription = Console.readLine("Enter new description for " + updtDishType.description() + ": ");
               updtDishType.changeDescriptionTo(newDescription);
               theController.changeDishType(updtDishType);
           } else { //DishType selected by user does not exist
               System.out.print("Invalid dish type. Select one of the items in the list.");
           }
               
        return true;
    }

    @Override
    public String headline() {
        return "Change Dish Type description";  
    }
    
}
