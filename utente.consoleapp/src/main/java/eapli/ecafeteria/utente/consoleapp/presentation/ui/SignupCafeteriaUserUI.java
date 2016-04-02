package eapli.ecafeteria.utente.consoleapp.presentation.ui;

import eapli.ecafeteria.application.AddUserController;
import eapli.ecafeteria.application.SignupCafeteriaUserController;
import eapli.ecafeteria.domain.Status;
import eapli.ecafeteria.domain.users.SystemUser;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupCafeteriaUserUI extends AbstractUI {

    private final SignupCafeteriaUserController theController = new SignupCafeteriaUserController();

    @Override
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        UserDataWidget userData = new UserDataWidget();

        final SystemUser user = (new AddUserController()).addUser(userData.username, userData.password, userData.firstName, userData.lastName, userData.email, null);

        final String account = Console.readLine("Account");
        final String OrganicUnit = Console.readLine("Account");
        final String mecanographicNumber = Console.readLine("Account");
        final Status status = Status.APPROVAL_PENDING;

        this.theController.addUser()

        return false;
    }

    @Override
    public String headline() {
        return "Sign Up";
    }

}
