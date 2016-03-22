/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp;

import eapli.ecafeteria.backoffice.consoleapp.presentation.MainMenu;
import eapli.ecafeteria.bootstrapapp.UsersBootstrap;
import eapli.framework.actions.Action;

/**
 *
 * @author pgsou_000
 */
public final class EapliEcafeteriaConsoleapp {

    public static void bootstrap(){
        // declare bootstrap actions
        final Action[] actions = { new UsersBootstrap(), };

        // execute all bootstrapping
        for (final Action boot : actions) {
            boot.execute();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        bootstrap();
        final MainMenu menu = new MainMenu();
        menu.mainLoop();
    }
}
