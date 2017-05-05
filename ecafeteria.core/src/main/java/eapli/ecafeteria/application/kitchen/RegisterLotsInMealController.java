/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * list of meals of day
     * @return 
     */
    public Iterable<Meal> showMealsOfDay(){
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        Calendar date = DateTime.now();
       return this.mealRepository.findByDate(DateTime.now());
 
    }
    
    /**
     * list of raw materials 
     * @return 
     */
    public Iterable<Material> showMaterials(){
         Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.materialRepository.findAll();
    }
    
    /**
     * bootstrap uses
     * @param meal meal 
     * @param material
     * @param batchNumber 
     * @throws eapli.framework.persistence.DataConcurrencyException 
     * @throws eapli.framework.persistence.DataIntegrityViolationException 
     */
    public void registerMaterialUsed(Meal meal, Material material, String batchNumber) throws DataConcurrencyException, DataIntegrityViolationException{
        final MaterialUsed materialUsed = new MaterialUsed(meal, material, batchNumber);
        materialUsedRepository.save(materialUsed);
    }
}
