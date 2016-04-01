package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 * Created by Monica on 29/03/2016.
 */
public class ListDishTypeController implements Controller {

    public Iterable<DishType> listDishTypes() {
        // FIXME check permissions
        final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();
        return dishTypeRepository.all();
    }

    // TODO why do we need this method in this controller? this controller's
    // name suggest it only list dish types. why is there a method to change the
    // dish type here?
    // TOFIXE save() in inMemoryRepository
    public void changeDishTypeState(DishType dType) {
        dType.changeDishTypeState();
        PersistenceContext.repositories().dishTypes();
    }
}