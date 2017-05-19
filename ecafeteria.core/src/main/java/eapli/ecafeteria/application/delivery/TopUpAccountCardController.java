package eapli.ecafeteria.application.delivery;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.Username;
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
import eapli.framework.persistence.repositories.TransactionalContext;

import javax.persistence.NoResultException;

/**
 * The controller to topUp an account card.
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class TopUpAccountCardController implements Controller {

    private final TransactionalContext txCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final AccountCardRepository accountCardsRepo = PersistenceContext.repositories().accountCards(txCtx);
    private final TransactionRepository transactionRepo = PersistenceContext.repositories().transactions(txCtx);

    /**
     * The account card.
     */
    private AccountCard anAccountCard;

    /**
     * Inserts the account card to topUp.
     *
     * @param mecanographicNumber the mecanographic number of the account card
     * @throws NoResultException exception launched when the mecanographic number is invalid
     */
    public void insertCard(String mecanographicNumber) throws NoResultException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.SALE);

        final MecanographicNumber aMecanographicNumber = new MecanographicNumber(mecanographicNumber);
        anAccountCard = accountCardsRepo.findByMecanographicNumber(aMecanographicNumber);
    }

    /**
     * TopUp the account card.
     *
     * @param eurosValue the amount to topUp
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public void topUpCard(Double eurosValue) throws DataConcurrencyException, DataIntegrityViolationException {
        Money aMoney = Money.euros(eurosValue);

        Username aCashier = Application.session().session().authenticatedUser().username();

        //explicitly begin a transaction
        txCtx.beginTransaction();
        /********************************/
        // Save new transaction
        Transaction aTransaction = new TopUp(anAccountCard, aMoney, aCashier);
        transactionRepo.save(aTransaction);
        // Add to card's balance
        aTransaction.notifyObservers();
        /********************************/
        //explicitly commit the transaction
        txCtx.commit();
    }
}
