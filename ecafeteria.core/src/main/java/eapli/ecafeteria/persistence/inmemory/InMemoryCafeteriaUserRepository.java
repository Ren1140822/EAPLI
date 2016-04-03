package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.CafeteriaUser;
import eapli.ecafeteria.domain.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 * 02/04/2016
 */
public class InMemoryCafeteriaUserRepository extends InMemoryRepository<CafeteriaUser, MecanographicNumber> implements CafeteriaUserRepository {

    @Override
    protected MecanographicNumber newPK(CafeteriaUser u) {
        return u.id();
    }
}
