package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.repositories.DataRepository;

import java.util.Calendar;

/**
 * Created by pyska on 27-04-2017.
 */
public interface MealRepository extends DataRepository<Meal, Long> {
    
    public Iterable<Meal> findByUntilDateAndWithoutAmountOfMealsPrepared(Calendar date);
    public Iterable<Meal> findByDate(Calendar date);
    public Iterable<Meal> findByDateAndMealType(Calendar date, MealType type);
    public Meal findByPk(Long pk);
   
}
