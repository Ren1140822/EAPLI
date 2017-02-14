package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.AuthenticationService;
import eapli.ecafeteria.domain.authz.Password;
import eapli.ecafeteria.domain.authz.UnableToAuthenticateException;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.ecafeteria.domain.authz.Username;
import eapli.framework.application.Controller;
import java.util.Optional;

/**
 * Created by nuno on 21/03/16.
 */
public class LoginController implements Controller {

    private final AuthenticationService authenticationService = new AuthenticationService();

    /**
     * This method allows a user to perform login and creates the session.
     *
     * @param userName
     * @param password
     */
    public void login(String userName, String password) throws UnableToAuthenticateException {
        final Optional<UserSession> newSession = this.authenticationService.authenticate(new Username(userName),
                new Password(password));
        if (newSession.isPresent()) {
            Application.session().setSession(newSession.get());
        } else {
            throw new UnableToAuthenticateException("Invalid user/pass");
        }
    }
}
