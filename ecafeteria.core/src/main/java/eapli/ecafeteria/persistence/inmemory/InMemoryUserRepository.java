package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.users.User;
import eapli.ecafeteria.domain.users.Username;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.domain.DomainEntity;
import eapli.framework.persistence.repositories.DeleteableRepository;
import eapli.framework.persistence.repositories.IterableRepository;
import eapli.framework.persistence.repositories.Repository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nuno on 20/03/16.
 */
public class InMemoryUserRepository extends InMemoryRepository<User, Username>
        implements UserRepository{

}
