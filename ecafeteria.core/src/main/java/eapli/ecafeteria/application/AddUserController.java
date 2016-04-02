package eapli.ecafeteria.application;

import java.util.Calendar;
import java.util.List;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.users.ActionRight;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.SystemUser;
import eapli.ecafeteria.domain.users.UserBuilder;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;

/**
 * FIXME this class is a duplicate of UserRegisterController.
 *
 * Created by nuno on 21/03/16.
 */
public class AddUserController implements Controller {

    public SystemUser addUser(String username, String password, String firstName, String lastName, String email,
            List<RoleType> roles, Calendar createdOn) throws DataIntegrityViolationException {

        if (!AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.Administer)) {
            // TODO check which exception to throw
            throw new IllegalStateException("user is not authorized to perform this action");
        }

        final UserBuilder userBuilder = new UserBuilder();
        userBuilder.withUsername(username).withPassword(password).withFirstName(firstName).withLastName(lastName)
                .withEmail(email).withRoles(roles).withCreatedOn(createdOn);

        final SystemUser newUser = userBuilder.build();
        final UserRepository userRepository = PersistenceContext.repositories().users();
        // TODO error checking if the username is already in the persistence
        // store
        userRepository.add(newUser);
        return newUser;
    }

    public SystemUser addUser(String username, String password, String firstName, String lastName, String email,
            List<RoleType> roles) throws DataIntegrityViolationException {
        return addUser(username, password, firstName, lastName, email, roles, DateTime.now());
    }
}
