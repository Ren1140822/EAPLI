package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaCafeteriaUserRepository extends CafeteriaJpaRepositoryBase<CafeteriaUser, MecanographicNumber>
        implements CafeteriaUserRepository {

    @Override
    public CafeteriaUser findByUsername(Username name) {
        // TODO use parameters instead of string concatenation
        return matchOne("e.systemUser.username.name='" + name + "'");
    }

    @Override
    public CafeteriaUser findByMecanographicNumber(MecanographicNumber number) {
        // TODO use parameters instead of string concatenation
        return matchOne("e.mecanographicNumber.number='" + number + "'");
    }
}
