package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.application.ListDishTypeController;
import eapli.ecafeteria.domain.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;


/**
 * Created by MCN on 29/03/2016.
 */
public class ListDishTypeUI extends AbstractUI {

    private final ListDishTypeController theController = new ListDishTypeController();

    @Override
    protected Controller controller() {
        return theController;
    }


    //TODO review doShowIterable implementation. Consider: generic type; reusable ui component
    @Override
    protected boolean doShow() {
        Iterable<DishType> iterable = theController.listDishTypes();
//        return doShowIterable(iterable);
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered Dish Type");
        } else {
            System.out.println("Listing Dish Types");
            System.out.printf("%-10s%-30s%-6s\n", "Acronym","Description", "Active");
            for (DishType dT : iterable) {
                System.out.printf("%-10s%-30s%-4s\n", dT.id(),dT.description(), String.valueOf(dT.isActive()));
            }
        }
        return true;
    }

    protected boolean doShowIterable(Iterable<DishType> iterable) {
        int i;
        
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered Dish Type");
        } else {
            System.out.printf("%-6s%-30s%6s\n", "Key", "Dish Type description", "Active");
            i = 0;
            for (DishType dT : iterable) {
                i++;
                System.out.printf("%-6d%-30s%6s\n", i, dT.description(), dT.isActive());
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "List Dish Types";
    }
}
