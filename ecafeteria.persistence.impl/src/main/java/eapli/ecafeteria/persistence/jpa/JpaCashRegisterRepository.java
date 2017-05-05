package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterId;
import eapli.ecafeteria.persistence.CashRegisterRepository;

/**
 *
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 *
 */
public class JpaCashRegisterRepository extends CafeteriaJpaRepositoryBase<CashRegister, Long>
        implements CashRegisterRepository {

    @Override
    public CashRegister findByCashRegisterId(CashRegisterId id) {
         return matchOne("e.cashRegisterId.id='" + id + "'");
    }

}
