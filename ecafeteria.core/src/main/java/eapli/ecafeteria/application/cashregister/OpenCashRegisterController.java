package eapli.ecafeteria.application.cashregister;

import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterId;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class OpenCashRegisterController implements Controller {

    private final CashRegisterRepository cashRegisterRepository = PersistenceContext.repositories().cashRegisters();
    private final ShiftRepository shiftRepository = PersistenceContext.repositories().shifts();

    public void openCashRegister(CashRegisterId cashRegisterId, MealType mealType, Calendar date)
            throws DataConcurrencyException, DataIntegrityViolationException {

        Shift shift = shiftRepository.findByDateAndMealType(date, mealType);

        //FIXME
        //creates a new shift if it doesn't exist in the DB
//        if (shift == null) {
//            shift = new Shift(date, mealType);
//        }
        //if shift is closed, the first cash register must open it
        shift.open();
        shiftRepository.save(shift);

        CashRegister cashRegister = cashRegisterRepository.findByCashRegisterId(cashRegisterId);
        cashRegister.open();
        cashRegisterRepository.save(cashRegister);
    }
}
