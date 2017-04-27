package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.account.AccountCard;
import eapli.framework.persistence.repositories.DataRepository;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public interface AccountCardRepository extends DataRepository<AccountCard, Long>, TransactionalContext {

    /**
     * returns the account card for the cafeteria user with the given mecanographic number
     *
     * @param number the mecanographic number
     * @return the account card for the cafeteria user with the given mecanographic number
     */
    AccountCard findByMecanographicNumber(MecanographicNumber number);
}
