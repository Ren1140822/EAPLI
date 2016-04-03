/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.application.ChangeDishTypeController;
import eapli.ecafeteria.application.ListDishTypeController;
import eapli.ecafeteria.domain.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.visitor.Visitor;
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
        allDishTypes.iterator();

        final SelectWidget<DishType> selector = new SelectWidget<DishType>(allDishTypes, new Visitor<DishType>() {

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
        });

        selector.show();
        final DishType updtDishType = selector.selectedElement();

        final String newDescription = Console
                .readLine("Enter new description for " + updtDishType.description() + ": ");
        updtDishType.changeDescriptionTo(newDescription);
        this.theController.changeDishType(updtDishType);

        return false;
    }

    @Override
    public String headline() {
        return "Change Dish Type description";
    }
}
