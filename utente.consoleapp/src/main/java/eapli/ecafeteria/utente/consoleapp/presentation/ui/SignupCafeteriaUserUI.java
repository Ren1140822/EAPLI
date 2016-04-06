package eapli.ecafeteria.utente.consoleapp.presentation.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.application.AddUserController;
import eapli.ecafeteria.application.SignupCafeteriaUserController;
import eapli.ecafeteria.domain.OrganicUnit;
import eapli.ecafeteria.domain.Status;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.utente.consoleapp.visitors.OrganicUnitUIVisitor;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupCafeteriaUserUI extends AbstractUI {

    private final SignupCafeteriaUserController theController = new SignupCafeteriaUserController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final UserDataWidget userData = new UserDataWidget();

        // FIXME do it in the right place ajs
        final List<RoleType> roles = new ArrayList<>();

        SystemUser user = null;
        try {
            user = (new AddUserController()).addUser(userData.username, userData.password, userData.firstName,
                    userData.lastName, userData.email, roles);
        } catch (final DataIntegrityViolationException ex) {
            Logger.getLogger(SignupCafeteriaUserUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        final String account = Console.readLine("Account");

        final SelectWidget<OrganicUnit> selector = new SelectWidget<OrganicUnit>(this.theController.getAllOrganicUnit(),
                new OrganicUnitUIVisitor());
        selector.show();

        final OrganicUnit organicUnit = selector.selectedElement();

        final String mecanographicNumber = Console.readLine("Mecanographic Number");

        if (user != null) {
            try {
                this.theController.addUser(user, account, organicUnit, mecanographicNumber, Status.APPROVAL_PENDING);
            } catch (final DataIntegrityViolationException ex) {
                Logger.getLogger(SignupCafeteriaUserUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Sign Up";
    }
}
