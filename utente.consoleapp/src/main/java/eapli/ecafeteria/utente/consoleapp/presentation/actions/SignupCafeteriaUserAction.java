/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.actions;

import eapli.ecafeteria.utente.consoleapp.presentation.ui.SignupCafeteriaUserUI;
import eapli.framework.actions.Action;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupCafeteriaUserAction implements Action {
    @Override
    public boolean execute() {
        return new SignupCafeteriaUserUI().show();
        //return new AddUserUI().show();
    }
}

