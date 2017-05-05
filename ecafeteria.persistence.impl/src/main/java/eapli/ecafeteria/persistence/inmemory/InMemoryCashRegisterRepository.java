package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterId;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 *
 */
public class InMemoryCashRegisterRepository extends InMemoryRepositoryWithLongPK<CashRegister>
        implements CashRegisterRepository {

    @Override
    public CashRegister findByCashRegisterId(CashRegisterId id) {
        return matchOne(e -> e.id().equals(id));
    }

}
