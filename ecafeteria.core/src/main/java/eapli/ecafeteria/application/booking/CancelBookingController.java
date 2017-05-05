/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.account.Refund;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class CancelBookingController implements Controller {

    private final ListBookingsService bookingsService = new ListBookingsService();
    private final BookingRepository bookingsRepository = PersistenceContext.repositories().bookings();
    private final CafeteriaUserRepository userRepository = PersistenceContext.repositories().cafeteriaUsers(null);

    public Iterable<Booking> listBookings() {
        //TODO is this call to the cafeteriauser repository really needed? or can we change the method parameter in the bookingService?
        CafeteriaUser client = userRepository.findByUsername(Application.session().session().authenticatedUser().username());
        return bookingsService.findBookingsStateDoneOf(client);
    }

    public void cancel(Booking booking) throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        Refund refund = booking.cancel();
        bookingsRepository.save(booking);
        //FIXME
        //@author Meireles
        // How to save the refund?
        // How to ensure that both saves work or both fail? (transactional control)
    }
}
