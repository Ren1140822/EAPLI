/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.ecafeteria.persistence.MealsPreparedRepository;

import java.util.HashMap;
import java.util.Map;


/**
 * JPA repository to manage prepared meals.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt] Diogo Santos [1150451@isep.ipp.pt]
 */
public class JpaMealsPreparedRepository extends CafeteriaJpaRepositoryBase<MealsPrepared, Long> implements MealsPreparedRepository {

    @Override
    public Iterable<MealsPrepared> findByShift(Shift shift) {
        Map<String, Object> params = new HashMap<>();
        params.put("shift", shift);
        return match("e.meal.date=:shift.date and e.meal.mealType=:shift.mealType", params);
    }


}