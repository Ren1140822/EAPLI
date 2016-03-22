package eapli.ecafeteria.backoffice.consoleapp.presentation;

import eapli.ecafeteria.application.LoginController;
import eapli.ecafeteria.domain.users.InvalidPasswordException;
import eapli.ecafeteria.domain.users.InvalidUserException;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

/**
 * Created by nuno on 21/03/16.
 */
public class LoginUI extends AbstractUI {
	private final LoginController theController = new LoginController();

	@Override
	protected Controller controller() {
		return theController;
	}

	@Override
	protected boolean doShow() {
		final String userName = Console.readLine("Username:");
		final String password = Console.readLine("Password:");

		try {
			theController.login(userName, password);
			System.out.println("Authentication Successful");
			return true;
		} catch (InvalidUserException | InvalidPasswordException e) {
			System.out.println("Invalid authentication");
			return false;
		}
	}

	@Override
	public String headline() {
		return "Login";
	}
}
