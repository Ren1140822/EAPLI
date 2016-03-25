package eapli.ecafeteria.utente.consoleapp;

import eapli.ecafeteria.bootstrapapp.ECafeteriaBootstrap;
import eapli.ecafeteria.utente.consoleapp.presentation.FrontMenu;

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
