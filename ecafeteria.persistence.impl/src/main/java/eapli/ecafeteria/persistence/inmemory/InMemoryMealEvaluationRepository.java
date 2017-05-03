/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealEvaluation;
import eapli.ecafeteria.persistence.MealEvaluationRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Pedro
 */
public class InMemoryMealEvaluationRepository extends InMemoryRepositoryWithLongPK<MealEvaluation>
        implements MealEvaluationRepository {

    @Override
    public Iterable<MealEvaluation> findByMeal(Meal meal) {
        return match(e -> e.isOfMeal(meal));
    }
    
}
