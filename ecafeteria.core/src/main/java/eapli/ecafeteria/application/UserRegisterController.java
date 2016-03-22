package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.users.Password;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.User;
import eapli.ecafeteria.domain.users.Username;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.ecafeteria.persistence.UserRepositoryImpl;

import java.util.List;

/**
 * Created by nuno on 20/03/16.
 */
public class UserRegisterController {

    public User registerUser(String username, String password, String firstName, String lastName, String email,
                             List<RoleType> roles) {

        User newUser = new User(username, password, firstName, lastName, email, roles);
        UserRepository userRepository = PersistenceContext.repositories().users();
        userRepository.save(newUser);
        return newUser;
    }
}
