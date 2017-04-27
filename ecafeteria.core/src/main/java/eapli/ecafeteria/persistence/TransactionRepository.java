package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.account.Transaction;
import eapli.framework.persistence.repositories.DataRepository;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public interface TransactionRepository extends DataRepository<Transaction, Long>, TransactionalContext {
}
