package eapli.ecafeteria.backoffice.presentation;

import eapli.framework.actions.Action;

/**
 * Created by nuno on 22/03/16.
 */
public class AddUserAction implements Action {
    @Override
    public boolean execute() {
        return new AddUserUI().show();
    }
}
