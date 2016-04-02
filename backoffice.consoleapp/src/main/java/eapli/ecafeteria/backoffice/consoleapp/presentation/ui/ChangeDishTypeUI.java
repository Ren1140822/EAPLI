/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import java.util.Iterator;

import eapli.ecafeteria.application.ChangeDishTypeController;
import eapli.ecafeteria.application.ListDishTypeController;
import eapli.ecafeteria.domain.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

/**
 *
 * @author Nuno
 */
public class ChangeDishTypeUI extends AbstractUI {

    private final ChangeDishTypeController theController = new ChangeDishTypeController();

    @Override
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        // TODO a UI class should only interact with one controller
        final Iterable<DishType> allDishTypes = new ListDishTypeController().listDishTypes();
        final Iterator<DishType> dishTypeIterator = allDishTypes.iterator();
        new ListDishTypeUI().doShowIterable(allDishTypes);

        final int dishTypeKey = Console.readInteger("Select dish type to change");
        int n = 0;
        // iterators do not implement random access, sequential access required
        // to reach the object selected by user
        DishType updtDishType = null;
        while (dishTypeIterator.hasNext() && n != dishTypeKey) {
            updtDishType = dishTypeIterator.next();
            n++;
        }

        if (dishTypeKey == n) { // DishType selected by user exists
            final String newDescription = Console
                    .readLine("Enter new description for " + updtDishType.description() + ": ");
            updtDishType.changeDescriptionTo(newDescription);
            this.theController.changeDishType(updtDishType);
        } else { // DishType selected by user does not exist
            System.out.print("Invalid dish type. Select one of the items in the list.");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Change Dish Type description";
    }
}
