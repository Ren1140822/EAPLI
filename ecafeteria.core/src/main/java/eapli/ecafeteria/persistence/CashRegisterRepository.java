package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterId;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 *
 */
public interface CashRegisterRepository extends DataRepository<CashRegister, Long> {

    /**
     * returns the cash register with the given cash register id.
     *
     * @param id the id of the cash register
     * @return the cash register with the given cash register id
     */
    CashRegister findByCashRegisterId(CashRegisterId id);
}
