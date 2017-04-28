/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.booking;

import eapli.framework.actions.Action;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class CancelBookingAction implements Action {

    @Override
    public boolean execute() {
        return new CancelBookingUI().show();
    }

}
