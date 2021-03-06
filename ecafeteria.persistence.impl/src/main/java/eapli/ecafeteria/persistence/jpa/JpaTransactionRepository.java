package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.cafeteria.account.Transaction;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * The jpa repository to manage transactions.
 *
 * Created by IvoFerro on 27/04/2017.
 */
public class JpaTransactionRepository extends JpaAutoTxRepository<Transaction, Long> implements TransactionRepository {

    /**
     * Creates a jpa transaction repository.
     *
     * @param autoTx the transactional context (if null repository will generate it)
     */
    public JpaTransactionRepository(TransactionalContext autoTx) {
        super(Application.settings().getPersistenceUnitName(), autoTx);
    }

}
