/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.framework.actions.Action;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class TopUpAccountCardAction implements Action {

    @Override
    public boolean execute() {
        return new TopUpAccountCardUI().show();
    }
}
