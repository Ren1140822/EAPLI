/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.framework.actions.Action;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class RegisterMealDeliveryAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterMealDeliveryUI().show();
    }
}
