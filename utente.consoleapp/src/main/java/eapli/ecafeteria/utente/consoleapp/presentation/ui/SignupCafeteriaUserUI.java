package eapli.ecafeteria.utente.consoleapp.presentation.ui;

import eapli.ecafeteria.application.AddUserController;
import eapli.ecafeteria.application.SignupCafeteriaUserController;
import eapli.ecafeteria.domain.OrganicUnit;
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
        
        final OrganicUnit  organicUnit = new OrganicUnit("ISEP", "Instituto Superior de Engenharia", "A nossa escolinha");
        final String mecanographicNumber = Console.readLine("Account");
 
        this.theController.addUser(user, account, organicUnit, mecanographicNumber, Status.APPROVAL_PENDING);

        return false;
    }

    @Override
    public String headline() {
        return "Sign Up";
    }

}
