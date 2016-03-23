package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.users.User;
import eapli.ecafeteria.domain.users.Username;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 * FIXME move to a separate project
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryUserRepository extends InMemoryRepository<User, Username> implements UserRepository {

}
