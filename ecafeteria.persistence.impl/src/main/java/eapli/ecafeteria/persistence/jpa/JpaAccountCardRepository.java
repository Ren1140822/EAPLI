package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.account.AccountCard;
import eapli.ecafeteria.persistence.AccountCardRepository;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class JpaAccountCardRepository extends CafeteriaJpaRepositoryBase<AccountCard, Long> implements AccountCardRepository {

    @Override
    public AccountCard findByMecanographicNumber(MecanographicNumber number) {

        return matchOne("e.mecanographicNumber.number='" + number + "'");
    }
}
