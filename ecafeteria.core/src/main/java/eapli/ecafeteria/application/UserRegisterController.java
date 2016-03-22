package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.users.Password;
import eapli.ecafeteria.domain.users.User;
import eapli.ecafeteria.domain.users.Username;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.ecafeteria.persistence.UserRepositoryImpl;

/**
 * Created by nuno on 20/03/16.
 */
public class UserRegisterController {

    public User registerUser(Username username, Password password)
    {
        User newUser = new User(username, password);
        UserRepository userRepository = PersistenceContext.repositories().users();
        userRepository.save(newUser);
        return newUser;
    }
}
