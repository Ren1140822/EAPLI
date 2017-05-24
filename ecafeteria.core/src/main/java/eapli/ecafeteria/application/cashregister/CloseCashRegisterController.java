/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cashregister;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterLog;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterState;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.cafeteria.cashregister.ShiftState;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CashRegisterLogRepository;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class CloseCashRegisterController {

    private final ShiftRepository shiftRepository = PersistenceContext.repositories().shifts();
    private final CashRegisterLogRepository cashRegisterLogRepository = PersistenceContext.repositories().cashRegisterLogs();
    private final CashRegisterRepository cashRegisterRepository = PersistenceContext.repositories().cashRegisters();
    private Shift currentShift;
    private Iterable<Shift> shiftIterable;
    private CashRegisterLog log;
    private CashRegister cashRegister;

    /**
     * Closes a cash register and saves logs.
     *
     * @param cashier the cashier that closes the cash register
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public void closeCashRegister(SystemUser cashier) throws DataConcurrencyException, DataIntegrityViolationException {
        shiftIterable = shiftRepository.findByState(ShiftState.OPENED);//only one shift should be opened, else the use case may give wrong results.
        currentShift = shiftIterable.iterator().hasNext() ? shiftIterable.iterator().next() : null;
        if (currentShift != null) {

            log = cashRegisterLogRepository.getOpenedCashRegisterLogByCashierInCurrentShift(cashier, currentShift);

            cashRegister = cashRegisterRepository.findByCashRegisterId(log.cashRegisterID());
            cashRegister.close();
            cashRegisterRepository.save(cashRegister);
            CashRegisterLog newLog = new CashRegisterLog(cashRegister, currentShift, cashier, CashRegisterState.CLOSED);
            cashRegisterLogRepository.save(newLog);

        }
    }

    /**
     * Counts delivered meals for this shift
     *
     * @return the number of delivered meals in this shift
     */
    public Long countDeliveredMeals() {
        BookingRepository bookingRepo = PersistenceContext.repositories().bookings(null);
        return bookingRepo.countAllDeliveredMeals(currentShift);
    }

    public Iterable<Booking> deliveredBookings() {

        BookingRepository bookingRepo = PersistenceContext.repositories().bookings(null);
        return bookingRepo.findBookingsDeliveredInShift(currentShift);

    }
}
