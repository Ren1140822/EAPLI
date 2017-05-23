/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * Repository to manage prepared meals.
 *
 * @author Sofia Silva [1150690@isep.ipp.pt] Diogo Santos [1150451@isep.ipp.pt]
 */
public interface MealsPreparedRepository extends DataRepository<MealsPrepared, Long> {

    /**
     * Find the meals prepared for a given shift.
     *
     * @param shift shift for the meals prepared
     * @return meals prepared for the shift
     */
    Iterable<MealsPrepared> findByShift(Shift shift);
}