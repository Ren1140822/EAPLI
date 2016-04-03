/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import org.h2.engine.User;

import eapli.ecafeteria.application.ListUsersController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author losa
 */
public class ListUsersUI extends AbstractUI {
    private final ListUsersController theController = new ListUsersController();

    @Override
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<SystemUser> iterable = this.theController.listUsers();
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered User");
        } else {
            for (final SystemUser user : iterable) {
                // TODO display other attributes
                System.out.printf("%10s %30s %30s\n", user.username(), user.name().firstName(), user.name().lastName());
            }
        }
        return false;
    }

    // TODO use a widget from the framework
    protected boolean doShowIterable(Iterable<User> iterable) {
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered Dish Type");
        } else {
            System.out.printf("%-6s%-30s%6s\n", "Key", "Dish Type description", "Active");
            /*
            for (final User dT : iterable) {
            }
            */
        }
        return true;
    }

    @Override
    public String headline() {
        return "List Users";
    }
}
