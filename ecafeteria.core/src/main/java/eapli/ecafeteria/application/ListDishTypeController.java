package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 * Created by Monica on 29/03/2016.
 */
public class ListDishTypeController implements Controller {

    public Iterable<DishType> listDishTypes() {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

        final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();
        return dishTypeRepository.all();
    }
}