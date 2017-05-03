/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class ListBookingsService {
    
    private final BookingRepository repo = PersistenceContext.repositories().bookings();

    public Iterable<Booking> findBookingsStateDoneOf(CafeteriaUser user) {
        return this.repo.findBookingByUserAndState(user, BookingState.DONE);
    }
    
    public Iterable<Booking> findBookingsStateDeliveredOf(CafeteriaUser user) {
        return this.repo.findBookingByUserAndState(user, BookingState.DELIVERED);
    }
    
}
