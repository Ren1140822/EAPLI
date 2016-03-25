package eapli.cafeteria.utente.consoleapp;

import eapli.cafeteria.utente.consoleapp.presentation.FrontMenu;
import eapli.ecafeteria.bootstrapapp.ECafeteriaBootstrap;

/**
 * eCafeteria User App
 *
 */
public class ECafeteriaUtenteApp {
	public static void main(String[] args) {

		// only needed because of the in memory persistence
		ECafeteriaBootstrap.bootstrap();

		new FrontMenu().show();
	}
}
