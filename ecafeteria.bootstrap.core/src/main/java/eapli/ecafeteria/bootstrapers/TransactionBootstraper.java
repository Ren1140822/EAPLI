package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.delivery.TopUpAccountCardController;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 *
 * @author Meireles
 */
public class TransactionBootstraper implements Action {

    @Override
    public boolean execute() {
        topUp("150330", 20);
        topUp("150331", 20);
        return false;
    }

    private void topUp(String mecanographicNumber, double amount) {
        TopUpAccountCardController controller = new TopUpAccountCardController();
        try {
            controller.insertCard(mecanographicNumber);
            controller.topUpCard(amount);
        } catch (final DataConcurrencyException | DataIntegrityViolationException e) {
            // assume it just a question of trying to insert duplicate record
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: Exception during Transaction bootstrapping: assuming existing record.");
        }
    }

}
