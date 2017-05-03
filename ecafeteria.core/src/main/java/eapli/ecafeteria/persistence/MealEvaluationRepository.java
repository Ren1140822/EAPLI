/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealEvaluation;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Pedro
 */
public interface MealEvaluationRepository extends DataRepository<MealEvaluation, Long>{
    
    Iterable<MealEvaluation> findByMeal(Meal meal);
    
}
