/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterLog;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterState;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.persistence.CashRegisterLogRepository;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class JpaCashRegisterLogRepository extends CafeteriaJpaRepositoryBase<CashRegisterLog, Long> implements CashRegisterLogRepository {

    @Override
    public CashRegisterLog getOpenedCashRegisterLogByCashierInCurrentShift(SystemUser cashier, Shift shift) {
        Map<String, Object> params = new HashMap<>();
        params.put("cashier", cashier);
        params.put("shift", shift);
        params.put("state", CashRegisterState.OPENED);
        Iterable<CashRegisterLog> regLogs = match("e.cashier=:cashier and e.shift=:shift and e.cashRegister.state=:state", params);
        LinkedList<CashRegisterLog> logList = new LinkedList();
         regLogs.iterator().forEachRemaining(logList::add);
         return logList.isEmpty()?matchOne("e.cashier=:cashier and e.shift=:shift and e.cashRegister.state=:state", params):logList.getLast(); //returns match one so it throws no such element exception if not found as match doesnt return it
    }
    
     @Override
    public CashRegisterLog getClosedCashRegisterLogByCashierInCurrentShift(SystemUser cashier, Shift shift) {
        Map<String, Object> params = new HashMap<>();
        params.put("cashier", cashier);
        params.put("shift", shift);
        params.put("state", CashRegisterState.CLOSED);
        Iterable<CashRegisterLog> regLogs = match("e.cashier=:cashier and e.shift=:shift and e.cashRegister.state=:state", params);
        LinkedList<CashRegisterLog> logList = new LinkedList();
         regLogs.iterator().forEachRemaining(logList::add);
         return logList.isEmpty()?matchOne("e.cashier=:cashier and e.shift=:shift and e.cashRegister.state=:state", params):logList.getLast(); //returns match one so it throws no such element exception if not found as match doesnt return it
    }

}
