package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.framework.actions.Action;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class OpenCashRegisterAction implements Action {

    @Override
    public boolean execute() {
        return new OpenCashRegisterUI().show();
    }

}
