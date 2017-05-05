package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.framework.actions.Action;

/**
* @author Pedro Fernandes (1060503@isep.ipp.pt) Diana Silva (1151088@isep.ipp.pt)
 */
public class RegisterLotsInMealAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterLotsInMealUI().show();
    }
    
}
