package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.users.SystemUser;
import eapli.ecafeteria.domain.users.Username;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 * 02/04/2016
 */
public class InMemoryCafeteriaUserRepository extends InMemoryRepository<SystemUser, Username> implements UserRepository {

    @Override
    protected Username newPK(SystemUser u) {
        return u.username();
    }
}
