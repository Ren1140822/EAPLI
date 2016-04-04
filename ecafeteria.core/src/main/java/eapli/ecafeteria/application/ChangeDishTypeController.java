/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author Nuno
 */
public class ChangeDishTypeController implements Controller {

    public DishType changeDishType(DishType updatedDishType) {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

        final DishTypeRepository repo = PersistenceContext.repositories().dishTypes();
        return repo.save(updatedDishType);
    }

    public Iterable<DishType> listDishTypes() {
        return new ListDishTypeService().listDishTypes();
    }
}
