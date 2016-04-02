package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.domain.users.ActionRight;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 * Created by Monica on 29/03/2016.
 */
public class ListDishTypeController implements Controller {

    public Iterable<DishType> listDishTypes() {
         if (!AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.ManageMenus)) {
            // TODO check which exception to throw
            throw new IllegalStateException("user is not authorized to perform this action");
        }
        final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();
        return dishTypeRepository.all();
    }
  
}