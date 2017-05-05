/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealEvaluation;
import eapli.ecafeteria.persistence.MealEvaluationRepository;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pedro
 */
public class JpaMealEvaluationRepository extends CafeteriaJpaRepositoryBase<MealEvaluation, Long> implements MealEvaluationRepository{

    @Override
    public Iterable<MealEvaluation> findByMeal(Meal meal) {
        Map<String, Object> params = new HashMap<>();
        params.put("meal", meal);
        return match("e.booking.meal = :meal", params);    
    }
    
}
