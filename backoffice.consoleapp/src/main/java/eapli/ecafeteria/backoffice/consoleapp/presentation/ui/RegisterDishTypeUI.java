/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.application.RegisterDishTypeController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

/**
 *
 * @author mcn
 */
public class RegisterDishTypeUI extends AbstractUI {
    private final RegisterDishTypeController theController = new RegisterDishTypeController();

    @Override
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final String acronym = Console.readLine("Dish Type Acronym:");
        final String description = Console.readLine("Dish Type Description:");

        // FIXME handle /prevent duplicates
        this.theController.registerDishType(acronym, description);
        return false;
    }

    @Override
    public String headline() {
        return "Register Dish Type";
    }
}
