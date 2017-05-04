package eapli.ecafeteria.application.delivery;

import eapli.ecafeteria.application.booking.ListBookingsService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.LinkedList;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class RegisterMealDeliveryController implements Controller {

    private final BookingRepository bookingRepo = PersistenceContext.repositories().bookings();

    private final CafeteriaUser user;

    public RegisterMealDeliveryController(CafeteriaUser user) {
        if (user == null) {
            throw new IllegalStateException();
        }
        this.user = user;

    }

    /**
     * Registers the last meal that this user booked.
     * @return  true if meal delivery was registered
     */
    public boolean registerMealDelivery() {

        ListBookingsService bookingsService = new ListBookingsService();
        LinkedList<Booking> bookingList = new LinkedList<>();
        bookingsService.findBookingsStateDefinitiveOf(user).iterator().forEachRemaining(bookingList::add);
        Booking tempBooking = bookingList.getLast();
        tempBooking.deliver();
        return true;
    }

    /**
     * Registers a specific meal of this user
     * @param meal the meal to deliver
     * @return true if the meal delivery was registered
     */
    public boolean registerMealDelivery(Meal meal) {
        Booking tempBooking = bookingRepo.findBookingByUserAndMealAndState(user, meal, BookingState.DEFINITIVE);
        tempBooking.deliver();
        return true;
    }

}
