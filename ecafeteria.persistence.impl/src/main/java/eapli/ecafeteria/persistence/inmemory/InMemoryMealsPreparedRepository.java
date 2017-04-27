/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.ecafeteria.persistence.MealsPreparedRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt] Diogo Santos [1150451@isep.ipp.pt]
 */
public class InMemoryMealsPreparedRepository extends InMemoryRepositoryWithLongPK<MealsPrepared> 
        implements MealsPreparedRepository{
    
}
