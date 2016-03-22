package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.users.*;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 * Created by nuno on 21/03/16.
 */
public class LoginController implements Controller {
    /**
     * This method allows a user to perform login.
     *
     * @param userName
     * @param password
     */
    public void login(String userName, String password) throws InvalidPasswordException, InvalidUserException {
        AuthenticationService authenticationService = new AuthenticationService();

        Username inputUserName = new Username(userName);
        Password inputPassword = new Password(password);

        Session newSession = authenticationService.authenticate(inputUserName, inputPassword);
        AppSettings.instance().setSession(newSession);
    }


}
