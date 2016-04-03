package eapli.cafeteria.consoleapp.presentation.ui;

import eapli.ecafeteria.application.LoginController;
import eapli.ecafeteria.domain.authz.UnableToAuthenticateException;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * UI for user login action. Created by nuno on 21/03/16.
 */
public class LoginUI extends AbstractUI {
    private final LoginController theController = new LoginController();

    /*
    private final Logger logger = LoggerFactory.getLogger(LoginUI.class);

    public Logger getLogger() {
        return logger;
    }
    */

    @Override
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final String userName = Console.readLine("Username:");
        final String password = Console.readLine("Password:");

        try {
            this.theController.login(userName, password);
            System.out.println("Authentication Successful");
            return true;
        } catch (final UnableToAuthenticateException e) {
            //getLogger().info("Invalid Authenticon:" + e);
            System.out.println("Invalid authentication");
            return false;
        }
    }

    @Override
    public String headline() {
        return "Login";
    }
}
