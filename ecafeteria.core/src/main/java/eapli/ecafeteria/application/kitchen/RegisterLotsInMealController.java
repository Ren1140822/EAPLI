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
import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Pedro Fernandes (1060503@isep.ipp.pt) Diana Silva (1151088@isep.ipp.pt)
 */
public class RegisterLotsInMealController implements Controller {
    
    private final MaterialRepository materialRepository = PersistenceContext.repositories().materials();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    
    private Material material;
    private MaterialUsed materialUsed;
    private Meal meal;
    
    /**
     * list of MealsPrepared the has not lotcode registration
     * @return 
     */
    public Iterable<Meal> showMealsWithoutRegistration(){
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        
        return this.mealRepository.findAll();
    }
    
    /**
     * selected Mealprepared by the user
     * @param preMeal
     * @return 
     */
    public Meal selectedMeal(String preMeal){
        
        // FIX ME
        meal = this.mealRepository.findByPk(Long.MIN_VALUE);
                
        return meal;
    }
    
    /**
     * selected Material by the user
     * @param material
     * @return 
     */
    public Material selectedMaterial(String material){
        
        this.material = this.materialRepository.findByAcronym(material);
        
        return this.material;
    }
    
    /**
     * insert material and lot in materialUsed
     * @param lotCode
     * @return 
     */
    public boolean fillMaterialAndLotCode(String lotCode){
        //FIXME
       /* BatchNumber a = new BatchNumber(lotCode);
        
        if( a == null || material == null){
            throw new IllegalStateException();
        }
        
        materialUsed = new MaterialUsed(material, a);
        
        return mealsPrepared.addMaterialsUsed(materialUsed);     */
       return true;
    }
    
    /**
     * update the meal prepared object in repository
     * @return 
     */
    public boolean saveRegistration() throws DataConcurrencyException, DataIntegrityViolationException{
        
        if (meal == null){
            return false;
        }

        this.mealRepository.save(meal);
        
        cleanObjects();
        
        return true;
    }
    
    /**
     * put all objects null, to inicialize new registration without leaving use case
     */
    private void cleanObjects(){
        material = null;
        materialUsed = null;
        meal = null;
    }
    

}
