package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import java.util.ArrayList;
import java.util.List;

import eapli.ecafeteria.application.ListDishTypeController;
import eapli.ecafeteria.domain.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

/**
 * Created by MCN on 29/03/2016.
 */
public class ActivateDeactivateDishTypeUI extends AbstractUI {

    private final ListDishTypeController theController = new ListDishTypeController();

    @Override
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final List<DishType> list = new ArrayList<>();
        final Iterable<DishType> iter = this.theController.listDishTypes();
        if (!iter.iterator().hasNext()) {
            System.out.println("There is no registered Dish Type");
        } else {
            int option;
            int cont = 1;
            System.out.printf("Nº :%30s---%6s\n", "Dish Type description ---", "Active");
            for (final DishType dT : iter) {
                list.add(dT);
                System.out.printf("%3d:%30s--- %1$B\n", cont, dT.description(), dT.isActive());
                cont++;
            }
            switch (option = Console.readInteger("Enter dish nº to change ative state or 0 to finish ")) {
            case 0:
                System.out.println("No dish type selected");
                break;
            default:
                this.theController.changeDishTypeState(list.get(option + 1));
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Activate / Deactivate Dish Types";
    }
}
