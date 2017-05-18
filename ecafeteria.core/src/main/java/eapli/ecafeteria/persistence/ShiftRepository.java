package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.cafeteria.cashregister.ShiftState;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

/**
 * Represents the shift repository interface.
 *
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

    /**
     * Obtains the shift with a given date, meal type and shift state.
     *
     * @param date the given date
     * @param mealType the given meal type
     * @param state the given shift state
     * @return the shift with a given date, meal type and shift state.
     */
    Shift findByDateAndMealTypeAndState(Calendar date, MealType mealType, ShiftState state);

    /**
     * Obtains a list of shifts with a given date.
     *
     * @param date the given date.
     * @return the shifts with a given date.
     */
    Iterable<Shift> findByDate(Calendar date);

    /**
     * Obtains a list of shifts with a given meal type.
     *
     * @param mealType the given meal type.
     * @return the shifts with a given meal type.
     */
    Iterable<Shift> findByMealType(MealType mealType);

    /**
     * Obtains the list of shifts with a given shift state.
     *
     * @param state the given shift state.
     * @return the shifts with a given shift state.
     */
    Iterable<Shift> findByState(ShiftState state);

}
