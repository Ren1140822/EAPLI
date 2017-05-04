package eapli.ecafeteria.application.delivery;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.account.AccountCard;
import eapli.ecafeteria.domain.cafeteria.account.TopUp;
import eapli.ecafeteria.domain.cafeteria.account.Transaction;
import eapli.ecafeteria.persistence.AccountCardRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class TopUpAccountCardController implements Controller {

    private final AccountCardRepository accountCardsRepo = PersistenceContext.repositories().accountCards();
    private final TransactionRepository transactionRepo = PersistenceContext.repositories().transactions();

    public void topUpCard(String mecanographicNumber, Double eurosValue)
            throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.SALE);

        MecanographicNumber aMecanographicNumber = new MecanographicNumber(mecanographicNumber);

        AccountCard card = accountCardsRepo.findByMecanographicNumber(aMecanographicNumber);

        Money aMoney = Money.euros(eurosValue);

        // Save new transaction
        Transaction aTransaction = new TopUp(aMecanographicNumber, aMoney);
        transactionRepo.save(aTransaction);
        // Add to card's balance
        card.topUp(aMoney);
        accountCardsRepo.save(card);
    }
}
