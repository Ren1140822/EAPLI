package eapli.ecafeteria.application.cashregister;

import eapli.ecafeteria.application.booking.ListBookingsService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterId;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import javax.persistence.NoResultException;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class OpenCashRegisterController implements Controller {

    private final CashRegisterRepository cashRegisterRepository = PersistenceContext.repositories().cashRegisters();
    private final ShiftRepository shiftRepository = PersistenceContext.repositories().shifts();
    private final BookingRepository bookingRepository = PersistenceContext.repositories().bookings(null);
    private final ListBookingsService bookings = new ListBookingsService();

    public void openCashRegister(CashRegisterId cashRegisterId)
            throws DataConcurrencyException, DataIntegrityViolationException {
        CashRegister cashRegister = cashRegisterRepository.findByCashRegisterId(cashRegisterId);
        cashRegister.open();
        cashRegisterRepository.save(cashRegister);
    }

    public void openShift(MealType mealType, Calendar date)
            throws DataConcurrencyException, DataIntegrityViolationException {
        //FIXME
        Shift shift;
        try {
            shift = shiftRepository.findByDateAndMealType(date, mealType);
        } catch (NoResultException ex) {
            shift = new Shift(date, mealType);
        }

        //if shift is closed, the first cash register must open it
        shift.open();
        shiftRepository.save(shift);

        Iterable<Booking> bookingsDoneState = bookings.findBookingsByDateAndMealTypeAndState(date, mealType, BookingState.DONE);

        for (Booking booking : bookingsDoneState) {
            booking.makeDefinitive();
            bookingRepository.save(booking);
        }

    }
}
