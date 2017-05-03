/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.framework.actions.Action;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt] Diogo Santos [1150451@isep.ipp.pt]
 */
public class RegistrationOfPreparedMealsAction implements Action {

    @Override
    public boolean execute() {
        return new RegistrationOfPreparedMealsUI().show();
    }
    
}
