/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealEvaluation;
import eapli.ecafeteria.persistence.MealEvaluationRepository;

/**
 *
 * @author Pedro
 */
public class JpaMealEvaluationRepository extends CafeteriaJpaRepositoryBase<MealEvaluation, Long> implements MealEvaluationRepository{

    @Override
    public Iterable<MealEvaluation> findByMeal(Meal meal) {
        //TO DO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
