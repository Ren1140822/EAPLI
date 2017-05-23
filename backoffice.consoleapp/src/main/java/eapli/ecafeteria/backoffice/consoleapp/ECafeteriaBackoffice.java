/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp;

import eapli.cafeteria.consoleapp.presentation.authz.LoginAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.MainMenu;

/**
 *
 * @author Paulo Gandra Sousa
 */
public final class ECafeteriaBackoffice {

    private static final int NUMBER_ATTEMPS = 3;

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private ECafeteriaBackoffice() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        // login and go to main menu

        for(int i=0; i<NUMBER_ATTEMPS;i++){
            if (new LoginAction().execute()) {
                final MainMenu menu = new MainMenu();
                menu.mainLoop();
                break;
            }
        }
    }
}
