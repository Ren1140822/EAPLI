/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.domain.users.ActionRight;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author mcn
 */
public class RegisterDishTypeController implements Controller {

    public DishType registerDishType(String acronym, String description) {
        if (!AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.ManageMenus)) { 
            // TODO check which exception to throw
            throw new IllegalStateException("user is not authorized to perform this action");
        }

        final DishType newDishType = new DishType(acronym, description);
        final DishTypeRepository repo = PersistenceContext.repositories().dishTypes();
        // FIXME error checking if the newDishType is already in the persistence
        // store
        repo.add(newDishType);
        return newDishType;
    }

}
