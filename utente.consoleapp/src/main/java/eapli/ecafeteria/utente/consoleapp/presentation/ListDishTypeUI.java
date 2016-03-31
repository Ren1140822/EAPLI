package eapli.ecafeteria.utente.consoleapp.presentation;

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

    @Override
    protected boolean doShow() {
        Iterable<DishType> iterable = theController.listDishTypes();
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered Dish Type");
        } else {
            System.out.printf("%30s---%6s\n", "Dish Type description ---", "Active");
            for (DishType dT : iterable) {
                System.out.printf("%30s--- %1$B\n", dT.description(), dT.isActive());
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "List Dish Types";
    }
}
