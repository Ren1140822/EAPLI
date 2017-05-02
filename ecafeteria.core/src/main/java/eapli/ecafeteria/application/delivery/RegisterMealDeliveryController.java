package eapli.ecafeteria.application.delivery;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.TimePeriod2;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class RegisterMealDeliveryController implements Controller {

    private final BookingRepository bookingRepo = PersistenceContext.repositories().bookings();
    private final CafeteriaUser user;
    private final TimePeriod2 timePeriod;

    public RegisterMealDeliveryController(CafeteriaUser user, TimePeriod2 timePeriod) {
        if (user == null || timePeriod == null) {
            throw new IllegalStateException();
        }
        this.user = user;
        this.timePeriod = timePeriod;
    }

    public boolean registerMealDelivery() {

        Booking tempBooking = bookingRepo.findAll().iterator().next(); //TODO: Find booking by current meal(?) and of this user

        tempBooking.deliver();
        return true;
    }

}
