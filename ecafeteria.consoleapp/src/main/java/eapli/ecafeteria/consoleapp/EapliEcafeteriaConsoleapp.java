/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.consoleapp;

import eapli.ecafeteria.presentation.MainMenu;

/**
 *
 * @author pgsou_000
 */
public final class EapliEcafeteriaConsoleapp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final MainMenu menu = new MainMenu();
        menu.mainLoop();
    }
}
