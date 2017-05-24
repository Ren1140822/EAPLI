package eapli.ecafeteria.application.delivery;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.booking.ListBookingsService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterLog;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.cafeteria.cashregister.ShiftState;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.CashRegisterLogRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class RegisterMealDeliveryController implements Controller {

    private final BookingRepository bookingRepo = PersistenceContext.repositories().bookings(null);

    private final CafeteriaUserRepository userRepo = PersistenceContext.repositories().cafeteriaUsers(null);

    private final CafeteriaUser user;

    public RegisterMealDeliveryController(String mecanographicNumberString) {
        MecanographicNumber mecanographicNumber = new MecanographicNumber(mecanographicNumberString);
        user = userRepo.findByMecanographicNumber(mecanographicNumber);
        if (user == null) {
            throw new IllegalStateException();
        }
    }

    /**
     * Registers the last meal that this user booked. [UC-CO-02]
     *
     * @return true if meal delivery was registered
     */
    public boolean registerMealDelivery() {

        ShiftRepository shiftRepo = PersistenceContext.repositories().shifts();
        CashRegisterLogRepository cashRegisterLogRepository = PersistenceContext.repositories().cashRegisterLogs();
        Iterable<Shift> shiftIterable = shiftRepo.findByState(ShiftState.OPENED);//only one shift should be opened, else the use case may give wrong results.
        Shift currentShift = shiftIterable.iterator().hasNext() ? shiftIterable.iterator().next() : null;
        if (currentShift != null) {

            try {

                CashRegisterLog oldLog = cashRegisterLogRepository.getClosedCashRegisterLogByCashierInCurrentShift(Application.session().session().authenticatedUser(), currentShift);//if cashier did not close a shift, this should be null
            } catch (NoResultException ex) {
                try {
                    ListBookingsService bookingsService = new ListBookingsService();
                    Booking tempBooking = bookingsService.findLatestBookingOfUserInDefinitiveState(user);

                    tempBooking.deliver();

                    bookingRepo.save(tempBooking);
                    return true;
                } catch (DataConcurrencyException | DataIntegrityViolationException | NoResultException ex2) {
                    Logger.getLogger(RegisterMealDeliveryController.class.getName()).log(Level.SEVERE, null, ex);

                }

            }
        }
        return false;
    }

}
