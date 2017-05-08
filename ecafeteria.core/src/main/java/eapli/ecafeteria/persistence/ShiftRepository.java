package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public interface ShiftRepository extends DataRepository<Shift, Long> {

    /**
     * returns the shift with the given date and meal type.
     *
     * @param date the given date
     * @param mealType the given meal type
     * 
     * @return the shift with the given date and meal type.
     */
    Shift findByDateAndMealType(Calendar date, MealType mealType);
}
