package eapli.ecafeteria.application.cashregister;

import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterId;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.application.Controller;
import java.util.Calendar;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class OpenCashRegisterController implements Controller {

    private final CashRegisterRepository cashRegisterRepository = PersistenceContext.repositories().cashRegisters();
    private final ShiftRepository shiftRepository = PersistenceContext.repositories().shifts();

    public void openCashRegister(CashRegisterId cashRegisterId, MealType mealType, Calendar date) {
        Shift shift = shiftRepository.findByDateAndMealType(date, mealType);
        CashRegister cashRegister = cashRegisterRepository.findByCashRegisterId(cashRegisterId);
        cashRegister.open();
    }
}
