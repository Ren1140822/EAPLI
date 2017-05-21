/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterLotsInMealController;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Fernandes
 */
public class MaterialUsedBootstraper implements Action{

    @Override
    public boolean execute() {
        final DishTypeRepository dishTypeRepo = PersistenceContext.repositories().dishTypes();
        final DishType fish = dishTypeRepo.findByAcronym("fish");
        final DishType meat = dishTypeRepo.findByAcronym("meat");
        final Dish dish1 = new Dish(fish, Designation.valueOf("Caster Of Fish"), Money.euros(75.0));
        final Dish dish2 = new Dish(meat, Designation.valueOf("Special Francesinha"), Money.euros(18.0));
        final Meal meal1 = new Meal(dish1, new MealType(MealType.MealTypes.LUNCH), Calendar.getInstance());
        final Meal meal2 = new Meal(dish2, new MealType(MealType.MealTypes.DINNER), Calendar.getInstance());
        final Material material = new Material("eggs", "eggs of the aviary");
        final Material materia2 = new Material("bread", "form bread");
        final String batchNumber1 = "kdsgf345";
        final String batchNumber2 = "ad34";
        
        try {
            register(meal1, material, batchNumber1);
            register(meal2, materia2, batchNumber2);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(MaterialUsedBootstraper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(MaterialUsedBootstraper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    private Calendar todaysCalendar(int dayShiftFromToday, int hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.add(Calendar.DAY_OF_MONTH, dayShiftFromToday);
        return calendar;
    }

    private void register(Meal meal, Material material, String batchnumber) throws DataConcurrencyException, DataIntegrityViolationException {
        final RegisterLotsInMealController controller = new RegisterLotsInMealController();
        controller.registerMaterialUsed(meal, material, batchnumber);
    }
}
