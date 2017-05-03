/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Diogo
 */
public class CheckExistingBookingController {

    private final BookingRepository repository = PersistenceContext.repositories().bookings();

    public Iterable<Booking> all() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.repository.findAll();
    }

}
