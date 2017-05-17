package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.cafeteria.cashregister.ShiftState;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Calendar;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class InMemoryShiftRepository extends InMemoryRepositoryWithLongPK<Shift>
        implements ShiftRepository {

    @Override
    public Shift findByDateAndMealType(Calendar date, MealType mealType) {
        return matchOne(e -> (e.isAtDate(date) && e.isOfMealType(mealType)));
    }

    @Override
    public Shift findByDateAndMealTypeAndState(Calendar date, MealType mealType, ShiftState state) {
        return matchOne(e -> (e.isAtDate(date) && e.isOfMealType(mealType) && e.isAtState(state)));
    }

    @Override
    public Iterable<Shift> findByDate(Calendar date) {
        return match(e -> (e.isAtDate(date)));
    }

    @Override
    public Iterable<Shift> findByMealType(MealType mealType) {
        return match(e -> (e.isOfMealType(mealType)));
    }

    @Override
    public Iterable<Shift> findByState(ShiftState state) {
        return match(e -> (e.isAtState(state)));
    }

}
