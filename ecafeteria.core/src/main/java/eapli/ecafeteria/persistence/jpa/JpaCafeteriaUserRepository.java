package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.CafeteriaUser;
import eapli.ecafeteria.domain.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 * 02/04/2016
 */
class JpaCafeteriaUserRepository extends JpaRepository<CafeteriaUser, MecanographicNumber> implements CafeteriaUserRepository {

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }

}
