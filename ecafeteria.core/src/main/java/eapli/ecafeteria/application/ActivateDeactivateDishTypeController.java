/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author mcn
 */
public class ActivateDeactivateDishTypeController implements Controller {
    
    public Iterable<DishType> listDishTypes(){
        return new ListDishTypeController().listDishTypes();
    } 
     public void changeDishTypeState(DishType dType) {
        if (!AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.ManageMenus)) {
            // TODO check which exception to throw
            throw new IllegalStateException("user is not authorized to perform this action");
        }
        dType.changeDishTypeState();
        final DishTypeRepository dishTypeRepository= PersistenceContext.repositories().dishTypes();
        dishTypeRepository.save(dType);
    }
    
}
