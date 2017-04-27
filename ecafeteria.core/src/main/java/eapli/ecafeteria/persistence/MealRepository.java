package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * Created by pyska on 27-04-2017.
 */
public interface MealRepository extends DataRepository<Meal, Long> {
    public Meal findByPk(Long pk);
}
