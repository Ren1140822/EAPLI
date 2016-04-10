package eapli.ecafeteria.backoffice.consoleapp.presentation.actions;

import eapli.ecafeteria.backoffice.consoleapp.presentation.ui.AcceptRefuseSignupRequestUI;
import eapli.ecafeteria.backoffice.consoleapp.presentation.ui.ActivateDeactivateDishTypeUI;
import eapli.framework.actions.Action;

/**
 * Created by AJS on 08/04/2016.
 */
public class AcceptRefuseSignupRequestAction implements Action {
    @Override
    public boolean execute() {
        return new AcceptRefuseSignupRequestUI().show();
    }
}
