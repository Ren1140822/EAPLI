package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.application.ListDishTypeController;
import eapli.ecafeteria.domain.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;

/**
 * Created by MCN on 29/03/2016.
 */
public class ListDishTypeUI extends AbstractUI {

    private final ListDishTypeController theController = new ListDishTypeController();

    @Override
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<DishType> dishTypes = this.theController.listDishTypes();
        if (!dishTypes.iterator().hasNext()) {
            System.out.println("There is no registered Dish Type");
        } else {
            System.out.println("Listing Dish Types");
            new ListWidget<DishType>(dishTypes, new Visitor<DishType>() {

                @Override
                public void visit(DishType visitee) {
                    System.out.printf("%-10s%-30s%-4s\n", visitee.id(), visitee.description(),
                            String.valueOf(visitee.isActive()));
                }

                @Override
                public void beforeVisiting(DishType visitee) {
                }

                @Override
                public void afterVisiting(DishType visitee) {
                }
            }).show();
        }
        return true;
    }

    @Override
    public String headline() {
        return "List Dish Types";
    }
}
