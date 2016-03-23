package eapli.ecafeteria.backoffice.consoleapp.presentation;

import eapli.framework.actions.Action;

/**
 * Created by nuno on 20/03/16.
 */
public class LoginAction implements Action {
    @Override
    public boolean execute() {
        return new LoginUI().show();
    }
}
