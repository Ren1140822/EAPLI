package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.account.AccountCard;
import eapli.ecafeteria.persistence.AccountCardRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class JpaAccountCardRepository extends JpaAutoTxRepository<AccountCard, MecanographicNumber> implements AccountCardRepository {

    public JpaAccountCardRepository(TransactionalContext autoTx) {
        super(Application.settings().getPersistenceUnitName(), autoTx);
    }

    @Override
    public AccountCard findByMecanographicNumber(MecanographicNumber number) {

        Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return repo.matchOne("e.mecanographicNumber=:number", params);
    }
}
