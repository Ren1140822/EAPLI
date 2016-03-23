package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.users.*;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;

import java.util.List;

/**
 * Created by nuno on 21/03/16.
 */
public class AddUserController implements Controller {

    public User addUser(String username, String password, String firstName, String lastName, String email,
                        List<RoleType> roles) {

        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setUsername(username);
        userBuilder.setPassword(password);
        userBuilder.setFirstName(firstName);
        userBuilder.setLastName(lastName);
        userBuilder.setEmail(email);
        userBuilder.setRoles(roles);

        User newUser = userBuilder.createUser();
        UserRepository userRepository = PersistenceContext.repositories().users();
        userRepository.save(newUser);
        return newUser;
    }
}
