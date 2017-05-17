/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.framework.actions.Action;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class RegisterComplaintAction implements Action{

    @Override
    public boolean execute() {
        return new RegisterComplaintUI().show();
    }
    
}
