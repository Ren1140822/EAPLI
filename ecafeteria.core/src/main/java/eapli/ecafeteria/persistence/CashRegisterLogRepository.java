/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterLog;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public interface CashRegisterLogRepository  extends DataRepository<CashRegisterLog, Long> {
    
    public CashRegisterLog getOpenedCashRegisterLogByCashierInCurrentShift(SystemUser cashier, Shift shift);
     public CashRegisterLog getClosedCashRegisterLogByCashierInCurrentShift(SystemUser cashier, Shift shift) ;
}
