/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.MaterialUsed;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MaterialUsedRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.ArrayList;

/**
 *
 * @author João
 */
public class SearchByLotController implements Controller {
    
    private final ListMaterialService svc = new ListMaterialService();
   
   
    public Iterable<Meal> showMealInMaterialsUsedByLot(String lotCode){
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return svc.showMealInMaterialsUsedByLot(lotCode);
    }
    
}
