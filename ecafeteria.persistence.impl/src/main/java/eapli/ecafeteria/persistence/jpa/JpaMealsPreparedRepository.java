/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.ecafeteria.persistence.MealsPreparedRepository;


/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt] Diogo Santos [1150451@isep.ipp.pt]
 */
public class JpaMealsPreparedRepository extends CafeteriaJpaRepositoryBase<MealsPrepared, Long> implements MealsPreparedRepository{

    @Override
    public MealsPrepared findByName(String acronym) {
        
        // TO DO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
