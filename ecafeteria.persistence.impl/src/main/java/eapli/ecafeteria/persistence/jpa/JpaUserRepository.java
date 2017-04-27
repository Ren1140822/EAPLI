package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
class JpaUserRepository extends JpaAutoTxRepository<SystemUser, Username>
        implements UserRepository {

    public JpaUserRepository(boolean autoTx) {
        super(Application.settings().getPersistenceUnitName(), autoTx);
    }
}
