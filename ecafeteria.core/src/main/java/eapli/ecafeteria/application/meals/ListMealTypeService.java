package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.PersistenceContext;

import java.util.ArrayList;

/**
 * Created by pyska on 26-04-2017.
 */
public class ListMealTypeService {
    public Iterable<MealType.MealTypes> allMealTypes() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        ArrayList<MealType.MealTypes> res = new ArrayList<>();
        for(MealType.MealTypes type : MealType.MealTypes.values()){
            res.add(type);
        }
        return res;
    }
}
