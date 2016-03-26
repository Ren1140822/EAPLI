package eapli.cafeteria.consoleapp.presentation.actions;

import eapli.framework.actions.Action;

/**
 * Menu action for user logout.
 * Created by nuno on 20/03/16.
 */
public class LogoutAction implements Action {
    @Override
    public boolean execute() {
        System.out.println("Logout");
        return false;
    }
}
