package eapli.ecafeteria.application.delivery;

import eapli.ecafeteria.persistence.AccountCardRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.application.Controller;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class TopUpAccountCardController implements Controller {

    private final AccountCardRepository accountCardsRepo = PersistenceContext.repositories().accountCards();
    private final TransactionRepository transactionRepo = PersistenceContext.repositories().transactions();

}
