/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.ecafeteria.persistence.MealsPreparedRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import eapli.util.DateTime;

/**
 * In memory repository to manage prepared meals.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt] Diogo Santos [1150451@isep.ipp.pt]
 */
public class InMemoryMealsPreparedRepository extends InMemoryRepositoryWithLongPK<MealsPrepared>
        implements MealsPreparedRepository {

    @Override
    public Iterable<MealsPrepared> findByShift(Shift shift) {
        //return match(e -> (e.id().getDate().equals(shift.date()) && e.id().mealType().equals(shift.mealType())));
        return match(e -> (DateTime.isSameDate(e.id().getDate(), shift.date()) && e.id().mealType().equals(shift.mealType())));
    }

}