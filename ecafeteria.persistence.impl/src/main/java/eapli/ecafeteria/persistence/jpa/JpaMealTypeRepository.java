package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;

/**
 * Created by pyska on 26-04-2017.
 */
public class JpaMealTypeRepository extends CafeteriaJpaRepositoryBase<MealType, String> implements MealTypeRepository {

    @Override
    public MealType findByPk(String mealType) {
        return matchOne("e.mealType='" + mealType + "'");
    }
}
