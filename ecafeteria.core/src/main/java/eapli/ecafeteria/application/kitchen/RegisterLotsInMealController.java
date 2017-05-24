package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.kitchen.MaterialUsed;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MaterialUsedRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author Pedro Fernandes (1060503@isep.ipp.pt) Diana Silva (1151088@isep.ipp.pt)
 */
public class RegisterLotsInMealController implements Controller {
    
    private final MaterialRepository materialRepository = PersistenceContext.repositories().materials();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    private final MaterialUsedRepository materialUsedRepository= PersistenceContext.repositories().materialUsed();
        
    /**
     * list meals of atual day
     * @return meals of today
     */
    public Iterable<Meal> showMealsOfDay(){
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

       Calendar date = DateTime.now();
       return this.mealRepository.findByDate(DateTime.now());
 
    }
    
    /**
     * list raw materials 
     * @return raw materials
     */
    public Iterable<Material> showMaterials(){
         Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.materialRepository.findAll();
    }
    
    /**
     * bootstrap uses
     * @param meal meal selected to introduce material used
     * @param material material used in the meal
     * @param batchNumber batch number (lot) which material came from
     * @throws eapli.framework.persistence.DataConcurrencyException 
     * @throws eapli.framework.persistence.DataIntegrityViolationException 
     */
    public void registerMaterialUsed(Meal meal, Material material, String batchNumber) throws DataConcurrencyException, DataIntegrityViolationException{
        final MaterialUsed materialUsed = new MaterialUsed(meal, material, batchNumber);
        materialUsedRepository.save(materialUsed);
    }
}
