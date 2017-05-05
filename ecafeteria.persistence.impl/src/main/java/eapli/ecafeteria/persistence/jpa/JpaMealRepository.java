/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eduangelo Ferreira
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {

    @Override
    public Meal findByPk(Long pk) {
        return matchOne("e.pk=pk", "pk", pk);
    }

    @Override
    public Iterable<Meal> findByDate(Calendar date) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        return match("e.date=:date", params);
    }
    
    @Override
    public Iterable<Meal> findByUntilDate(Calendar date) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        return match("e.date<=:date", params);
    }

    @Override
    public Iterable<Meal> findByDateAndMealType(Calendar date, MealType.MealTypes type) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        params.put("type", type);
        return match("e.date=:date and e.mealType=:type", params);
    }



}
