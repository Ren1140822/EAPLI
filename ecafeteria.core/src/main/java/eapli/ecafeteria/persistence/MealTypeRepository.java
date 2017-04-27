package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * Created by pyska on 26-04-2017.
 */
public interface MealTypeRepository extends DataRepository<MealType, String> {
    public MealType findByPk(String mealType);
}
