package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaCafeteriaUserRepository
        extends JpaAutoTxRepository<CafeteriaUser, MecanographicNumber>
        implements CafeteriaUserRepository {

    public JpaCafeteriaUserRepository(boolean autoTx) {
        super(Application.settings().getPersistenceUnitName(), autoTx);
    }

    @Override
    public CafeteriaUser findByUsername(Username name) {
        // TODO use parameters instead of string concatenation
        return repo.matchOne("e.systemUser.username.name='" + name + "'");
    }

    @Override
    public CafeteriaUser findByMecanographicNumber(MecanographicNumber number) {
        // TODO use parameters instead of string concatenation
        return repo.matchOne("e.mecanographicNumber.number='" + number + "'");
    }
}
