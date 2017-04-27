package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.account.Transaction;
import eapli.ecafeteria.persistence.TransactionRepository;

/**
 * Created by IvoFerro on 27/04/2017.
 */
public class JpaTransactionRepository extends CafeteriaJpaRepositoryBase<Transaction, Long> implements TransactionRepository {
}
