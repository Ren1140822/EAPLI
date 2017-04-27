package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.account.Transaction;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 * Created by IvoFerro on 27/04/2017.
 */
public class InMemoryTransactionRepository extends InMemoryRepositoryWithLongPK<Transaction> implements TransactionRepository {
}
