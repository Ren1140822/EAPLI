/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp;

import eapli.cafeteria.consoleapp.presentation.LoginAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.MainMenu;
import eapli.ecafeteria.bootstrapapp.ECafeteriaBootstrap;

/**
 *
 * @author pgsou_000
 */
public final class ECafeteriaBackoffice {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		// only needed because of the in memory persistence
		ECafeteriaBootstrap.bootstrap();

		// login and go to main menu
		// TODO should provide three attempts
		if (new LoginAction().execute()) {
			final MainMenu menu = new MainMenu();
			menu.mainLoop();
		}
	}
}
