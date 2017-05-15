package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.cafeteria.cashregister.ShiftState;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.ShiftRepository;
import java.util.Calendar;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class JpaShiftRepository extends CafeteriaJpaRepositoryBase<Shift, Long>
        implements ShiftRepository {

    @Override
    public Shift findByDateAndMealType(Calendar date, MealType mealType) {
        return matchOne("e.date=:" + date + " and e.mealType=:" + mealType);
    }

    public Iterable<Shift> findByDate(Calendar date) {
        return match("e.date=:" + date);
    }

    public Iterable<Shift> findByMealType(MealType mealType) {
        return match("e.mealType=:" + mealType);
    }

    public Iterable<Shift> findByState(ShiftState state) {
        return match("e.shift=:" + state);
    }

    public Shift findByDateAndMealTypeAndState(Calendar date, MealType mealType, ShiftState state) {
        return matchOne("e.date=:" + date + " and e.mealType=:" + mealType + " and e.shiftState=:" + state);
    }

}
