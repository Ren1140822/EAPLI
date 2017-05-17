/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.account.Refund;
import eapli.ecafeteria.domain.cafeteria.account.Transaction;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * The controller to select and cancel a booking of the system user.
 *
 * @author Miguel Silva - 1150901
 */
public class CancelBookingController implements Controller {

    private final CafeteriaUserService usersService = new CafeteriaUserService();
    private final ListBookingsService bookingsService = new ListBookingsService();
    private final TransactionalContext txCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final BookingRepository bookingsRepository = PersistenceContext.repositories().bookings(txCtx);
    private final TransactionRepository transactionsRepository = PersistenceContext.repositories().transactions(txCtx);

    /**
     * It provides all cancelable bookings of the current system user.
     *
     * @return It returns an iterable with all the cancelable bookings of the
     * current system user.
     */
    public Iterable<Booking> listBookings() {
        Username username = Application.session().session().authenticatedUser().username();
        CafeteriaUser client = usersService.findCafeteriaUserByUsername(username);
        return bookingsService.findBookingsStateDoneOf(client);
    }

    /**
     * It cancels the booking and refunds the cafeteria user.
     *
     * @param booking The booking to be canceled.
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public void cancel(Booking booking) throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        booking.cancel();
        Transaction refund = booking.refund();
        refund.notifyObservers();
        txCtx.beginTransaction();
        bookingsRepository.save(booking);
        transactionsRepository.save(refund);
        txCtx.commit();
    }
}
