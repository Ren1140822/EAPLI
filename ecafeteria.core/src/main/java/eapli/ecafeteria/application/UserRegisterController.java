package eapli.ecafeteria.application;

import java.util.List;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.users.ActionRight;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.User;
import eapli.ecafeteria.domain.users.UserBuilder;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;

/**
 * FIXME this class is a duplicate of AddUserController.
 *
 * Created by nuno on 20/03/16.
 */
public class UserRegisterController {

    public User registerUser(String username, String password, String firstName, String lastName, String email,
            List<RoleType> roles) {
        if (!AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.Administer)) { 
            // TODO check which exception to throw
            throw new IllegalStateException("user is not authorized to perform this action");
        }

        final UserBuilder userBuilder = new UserBuilder();
        userBuilder.setUsername(username);
        userBuilder.setPassword(password);
        userBuilder.setFirstName(firstName);
        userBuilder.setLastName(lastName);
        userBuilder.setEmail(email);
        userBuilder.setRoles(roles);

        final User newUser = userBuilder.createUser();
        final UserRepository userRepository = PersistenceContext.repositories().users();
        // TODO error checking if the username is already in the persistence
        // store
        userRepository.add(newUser);
        return newUser;
    }
}
