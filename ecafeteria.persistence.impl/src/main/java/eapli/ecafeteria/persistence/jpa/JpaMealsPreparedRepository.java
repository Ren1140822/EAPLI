/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
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
        params.put("date", shift.date());
        params.put("mealType", shift.mealType());
        return match("e.meal.date=:date and e.meal.mealType=:mealType", params);
    }

    @Override
     public Long countWithBookedState(Booking booking, BookingState state){  
         Map<String,Object> params = new HashMap<>();
         params.put("state", state);
         params.put("meal", booking.meal());
         
         String whereClause= "e.state=:state and e.meal=:meal";

        return count(whereClause, params);
     }     
}