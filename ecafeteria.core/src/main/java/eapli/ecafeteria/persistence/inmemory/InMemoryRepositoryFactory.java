package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.UserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    private static UserRepository userRepository = null;

    @Override
    public UserRepository users() {
        if (userRepository == null) {
            userRepository = new InMemoryUserRepository();
        }
        return userRepository;
    }
}
