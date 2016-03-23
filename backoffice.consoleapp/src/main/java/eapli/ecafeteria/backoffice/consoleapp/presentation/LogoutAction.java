package eapli.ecafeteria.backoffice.consoleapp.presentation;

import eapli.framework.actions.Action;

/**
 * Created by nuno on 20/03/16.
 */
public class LogoutAction implements Action {
    @Override
    public boolean execute() {
        System.out.println("Logout");
        return false;
    }
}
