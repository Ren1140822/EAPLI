/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.MaterialUsed;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Pedro Fernandes
 */
public interface MaterialUsedRepository extends DataRepository<MaterialUsed, Long>{
    
    public Iterable<MaterialUsed> searchByLot(String lotCode);
    
}
