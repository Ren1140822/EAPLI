package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.dishtype.DishType;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.framework.application.Controller;

import java.util.List;

/**
 * Created by Monica on 29/03/2016.
 */
public class ListDishTypeController implements Controller {

    public Iterable<DishType> listDishTypes(){
        DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();
        return dishTypeRepository.all();
    }

    public void changeDishTypeState(DishType dType){
        dType.changeDishTypeState();
        DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();

    }
}