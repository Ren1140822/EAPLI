/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.BatchNumber;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.kitchen.MaterialUsed;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MaterialUsedRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.TimePeriod2;
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
    
    private Material material;
    private MaterialUsed materialUsed;
    private Meal meal;
    private BatchNumber batchNumber;
    
    /**
     * list of meals of day
     * @return 
     */
    public Iterable<Meal> showMealsOfDay(){
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        
       return this.mealRepository.findByDate(buildPeriodToday());
 
    }
    
    // FIXME
    //responsability of return time period of today isnt from controller 
    private TimePeriod2 buildPeriodToday(){
        Calendar start= DateTime.now();
        Calendar end= DateTime.tomorrow();
        TimePeriod2 today=new TimePeriod2(start, end);
        return today;
    }
    
    
    /**
     * Selection of meal to introduce material used
     * @param acron acronym of meal
     * @return true if valid, false if not
     */
    public boolean selectMeal(Long acron){    
        this.meal=this.mealRepository.findByPk(acron);
        return meal != null;
    }
    
    /**
     * selection of raw material
     * @param acronym of raw material
     * @return true if valid, false if not
     */
    public boolean selectMaterial(String acronym){
        this.material=this.materialRepository.findByAcronym(acronym);
        return material!=null;
    }
    
    /**
     * selection of lot
     * @param lot lot identification
     * @return true if valid, false if not
     */
    public boolean selectLot(String lot){
        this.batchNumber=new BatchNumber(lot);
        return batchNumber!=null;
    }
    
    /**
     * insert material and lot in materialUsed
     * @param lotCode
     * @return true if material used is valid, false if not
     */
    public boolean fillMaterialUsed(String lotCode){
        
        this.materialUsed = new MaterialUsed(meal,material,batchNumber);
      
       return materialUsed!=null;
    }
    
    /**
     * update the meal prepared object in repository
     * @return 
     * @throws eapli.framework.persistence.DataConcurrencyException 
     * @throws eapli.framework.persistence.DataIntegrityViolationException 
     */
    public boolean saveRegistration() throws DataConcurrencyException, DataIntegrityViolationException{

        materialUsedRepository.save(materialUsed);
        cleanObjects();
        
        return true;
    }
    
    /**
     * bootstrap uses
     * @return 
     */
    public void registerMaterialUsed(Meal meal, Material material, String batchNumber) throws DataConcurrencyException, DataIntegrityViolationException{
        materialUsed = new MaterialUsed(meal, material, new BatchNumber(batchNumber));
        materialUsedRepository.save(materialUsed);
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
