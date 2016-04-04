/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.application.ChangeDishTypeController;
import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
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
        //final Iterable<DishType> allDishTypes = new ListDishTypeController().listDishTypes();
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

        final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();
        final Iterable<DishType> allDishTypes = dishTypeRepository.all();
        //allDishTypes.iterator();
             
        //Note: Java no longer requires explicit type argument, thus new SelectWidget<DishType> may be replaced by new SelectWidget<>
        final SelectWidget<DishType> selector = new SelectWidget<DishType>(allDishTypes, new DishTypePrinter());

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
