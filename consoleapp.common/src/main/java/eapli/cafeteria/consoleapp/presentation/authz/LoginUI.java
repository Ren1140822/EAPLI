package eapli.cafeteria.consoleapp.presentation.authz;

import eapli.ecafeteria.application.authz.LoginController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;

/**
 * UI for user login action. Created by nuno on 21/03/16.
 */
public class LoginUI extends AbstractUI {

    private final LoginController theController = new LoginController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final String userName = Console.readLine("Username:");
        final String password = Console.readLine("Password:");

        if (this.theController.login(userName, password)) {
            System.out.println("Authentication Successful");
            return true;
        } else {
            // getLogger().info("Invalid Authenticon:" + e);
            System.out.println("Invalid authentication");
            return false;
        }
    }

    @Override
    public String headline() {
        return "Login";
    }
}
