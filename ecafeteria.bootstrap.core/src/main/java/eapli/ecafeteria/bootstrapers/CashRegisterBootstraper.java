package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterId;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class CashRegisterBootstraper implements Action {

    @Override
    public boolean execute() {
        final CashRegisterId id1 = new CashRegisterId("10001");
        final CashRegisterId id2 = new CashRegisterId("10002");
        final CashRegisterId id3 = new CashRegisterId("10003");
        
        register(id1);
        register(id2);
        register(id3);
        
        return false;
    }

    private void register(CashRegisterId identifier) {
        final CashRegisterRepository cashRegisters = PersistenceContext.repositories().cashRegisters();
        try {
            cashRegisters.save(new CashRegister(identifier));
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-BO001: bootstrapping existing record");
        }
    }

}
