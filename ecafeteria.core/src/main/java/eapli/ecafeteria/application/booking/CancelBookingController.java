/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class CancelBookingController implements Controller {
    
    private final ListBookingsService bookingsService = new ListBookingsService();
    private final BookingRepository bookingsRepository = PersistenceContext.repositories().bookings();
    private final CafeteriaUserRepository userRepository = PersistenceContext.repositories().cafeteriaUsers(false);
    
    public Iterable<Booking> listBookings(){
        CafeteriaUser client = userRepository.findByUsername(Application.session().session().authenticatedUser().username());
        return bookingsService.findBookingsStateDoneOf(client);
    }
    
    public void cancel(Booking booking) throws DataConcurrencyException, DataIntegrityViolationException{
        Application.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        booking.cancel();
        bookingsRepository.save(booking);
    }
}
