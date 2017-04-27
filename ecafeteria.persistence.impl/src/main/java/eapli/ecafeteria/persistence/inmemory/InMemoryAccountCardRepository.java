package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.account.AccountCard;
import eapli.ecafeteria.persistence.AccountCardRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class InMemoryAccountCardRepository extends InMemoryRepositoryWithLongPK<AccountCard>
        implements AccountCardRepository {

    @Override
    public AccountCard findByMecanographicNumber(MecanographicNumber number) {

        return matchOne(e -> e.id().id().equals(number));
    }
}
