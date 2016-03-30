package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.application.ListDishTypeController;
import eapli.ecafeteria.domain.dishtype.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MCN on 29/03/2016.
 */
public class ActivateDeactivateDishTypeUI extends AbstractUI {

    private final ListDishTypeController theController = new ListDishTypeController();

    @Override
    protected Controller controller() {
        return theController;
    }

    @Override
    protected boolean doShow() {
        List<DishType> list = new ArrayList<>();
        Iterable<DishType> iter = theController.listDishTypes();
        if (!iter.iterator().hasNext()) {
            System.out.println("There is no registered Dish Type");
        } else {
            int option;
            int cont = 1;
            System.out.printf("Nº :%30s---%6s\n", "Dish Type description ---", "Active");
            for (DishType dT : iter) {
                list.add(dT);
                System.out.printf("%3d:%30s--- %1$B\n", cont, dT.description(), dT.isActive());
                cont++;
            }
            switch (option = (int) Console.readInteger("Enter dish nº to change ative state or 0 to finish ")) {
                case 0:
                    System.out.println("No dish type selected");
                    break;
                default:
                    theController.changeDishTypeState(list.get(option + 1));
            }

        }
        return true;
    }

    @Override
    public String headline() {
        return "Activate / Deactivate Dish Types";
    }
}
