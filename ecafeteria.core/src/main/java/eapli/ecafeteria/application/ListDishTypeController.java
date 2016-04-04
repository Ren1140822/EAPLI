package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.DishType;
import eapli.framework.application.Controller;

/**
 * Created by Monica on 29/03/2016.
 */
public class ListDishTypeController implements Controller {

    public Iterable<DishType> listDishTypes() {
        return new ListDishTypeService().listDishTypes();
    }
}