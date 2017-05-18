package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.cafeteria.cashregister.ShiftState;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.ShiftRepository;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the JPA shift repository.
 *
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class JpaShiftRepository extends CafeteriaJpaRepositoryBase<Shift, Long>
        implements ShiftRepository {

    @Override
    public Shift findByDateAndMealType(Calendar date, MealType mealType) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        params.put("type", mealType);
        return matchOne("e.date=:date and e.mealType=:type", params);
    }

    @Override
    public Iterable<Shift> findByDate(Calendar date) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        return match("e.date=:date", params);
    }

    @Override
    public Iterable<Shift> findByMealType(MealType mealType) {
        Map<String, Object> params = new HashMap<>();
        params.put("mealType", mealType);
        return match("e.mealType=:mealType", params);
    }

    @Override
    public Iterable<Shift> findByState(ShiftState state) {
        Map<String, Object> params = new HashMap<>();
        params.put("state", state);
        return match("e.state=:state", params);
    }

    @Override
    public Shift findByDateAndMealTypeAndState(Calendar date, MealType mealType, ShiftState state) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        params.put("type", mealType);
        params.put("state", state);
        return matchOne("e.date=:date and e.mealType=:type and e.state=:state", params);
    }

}
