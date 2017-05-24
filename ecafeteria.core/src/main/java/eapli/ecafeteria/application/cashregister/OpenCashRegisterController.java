package eapli.ecafeteria.application.cashregister;

import eapli.ecafeteria.application.booking.ListBookingsService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterId;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.cafeteria.cashregister.ShiftState;
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
 * Controller responsible for opening cash registers.
 *
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class OpenCashRegisterController implements Controller {

    private final CashRegisterRepository cashRegisterRepository = PersistenceContext.repositories().cashRegisters();
    private final ShiftRepository shiftRepository = PersistenceContext.repositories().shifts();
    private final BookingRepository bookingRepository = PersistenceContext.repositories().bookings(null);
    private final ListBookingsService bookings = new ListBookingsService();

    /**
     * Opens the cash register with a cash register id passed as parameter.
     *
     * @param cashRegisterId the cash register id.
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public void openCashRegister(CashRegisterId cashRegisterId, MealType mealType, Calendar date)
            throws DataConcurrencyException, DataIntegrityViolationException {
        CashRegister cashRegister = cashRegisterRepository.findByCashRegisterId(cashRegisterId);
        cashRegister.open();
        openShift(mealType, date);
        cashRegisterRepository.save(cashRegister);
    }

    /**
     * Opens the shift with a meal type and date passed as parameters.
     *
     * @param mealType the meal type.
     * @param date the date.
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public void openShift(MealType mealType, Calendar date)
            throws DataConcurrencyException, DataIntegrityViolationException {
        
    //FIXME
    //@author Meireles
    // Should this method be public? Is it used by the UI or only within the controller?
    
        Shift shift;
        try {
            shift = shiftRepository.findByDateAndMealType(date, mealType);
        } catch (NoResultException ex) {
            shift = new Shift(date, mealType);

            Iterable<Booking> bookingsDoneState = bookings.findBookingsByDateAndMealTypeAndState(date, mealType, BookingState.DONE);

            for (Booking booking : bookingsDoneState) {
                booking.makeDefinitive();
                bookingRepository.save(booking);
            }
            shiftRepository.save(shift);
        }

        if(shift.isAtState(ShiftState.CLOSED)){
            throw new IllegalStateException("The shift is already closed and cannot be open!");
        }

    }
}
