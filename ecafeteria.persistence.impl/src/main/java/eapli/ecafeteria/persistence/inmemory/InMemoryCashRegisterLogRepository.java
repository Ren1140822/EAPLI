/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterLog;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.persistence.CashRegisterLogRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class InMemoryCashRegisterLogRepository  extends InMemoryRepositoryWithLongPK<CashRegisterLog>
        implements CashRegisterLogRepository {

    @Override
    public CashRegisterLog getOpenedCashRegisterLogByCashierInCurrentShift(SystemUser cashier,Shift shift) {
       return null;
    }

    @Override
    public CashRegisterLog getClosedCashRegisterLogByCashierInCurrentShift(SystemUser cashier, Shift shift) {
      return null;
    }

    

    
}
