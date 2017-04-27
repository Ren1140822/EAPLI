package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 * Created by pyska on 26-04-2017.
 */
public class InMemoryMealTypeRepository extends InMemoryRepository<MealType, String> implements MealTypeRepository {

    @Override
    public MealType findByPk(String mealType) {
        return matchOne(e -> e.mealType().equals(mealType));
    }

    @Override
    protected String newPK(MealType entity) {
        return entity.mealType();
    }
}
