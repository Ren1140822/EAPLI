package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.repositories.DataRepository;

import java.util.Calendar;

/**
 * Created by pyska on 27-04-2017.
 */
public interface MealRepository extends DataRepository<Meal, Long> {
    
    public Iterable<Meal> findByDate(TimePeriod2 timePeriod2);
    public Meal findByPk(Long pk);
}
