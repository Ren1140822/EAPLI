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
 * @author Nuno
 */
public class ChangeDishTypeController implements Controller {

    public DishType changeDishType(DishType updatedDishType) {
        
        final DishTypeRepository repo = PersistenceContext.repositories().dishTypes();
        // store
        repo.save(updatedDishType);
        return updatedDishType;
    }

}
