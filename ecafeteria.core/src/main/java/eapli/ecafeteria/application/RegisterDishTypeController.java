/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author mcn
 */
public class RegisterDishTypeController implements Controller {

    public DishType registerDishType(String acronym, String description) {
        
        final DishType newDishType = new DishType(acronym, description);
        final DishTypeRepository repo = PersistenceContext.repositories().dishTypes();
        // TODO error checking if the newDishType is already in the persistence
        // store
        repo.add(newDishType);
        return newDishType;
    }

    
}
