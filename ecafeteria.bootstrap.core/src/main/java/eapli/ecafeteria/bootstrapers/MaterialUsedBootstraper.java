package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterLotsInMealController;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Fernandes
 */
public class MaterialUsedBootstraper implements Action {

    @Override
    public boolean execute() {
        final Calendar yesterday = DateTime.yesterday();
        final Calendar today = DateTime.now();

        final MealType lunch = new MealType(MealType.MealTypes.LUNCH);
        final MealType dinner = new MealType(MealType.MealTypes.DINNER);
        
        final MealRepository meals = PersistenceContext.repositories().meals();

        Iterator<Meal> list = meals.findByDateAndMealType(yesterday, dinner).iterator();
        final Meal yesterdayDinnerA = list.next();
        final Meal yesterdayDinnerB = list.next();

        list = meals.findByDateAndMealType(today, lunch).iterator();
        final Meal todayLunchA = list.next();
        final Meal todayLunchB = list.next();

        final MaterialRepository materials = PersistenceContext.repositories().materials();
        final Material eggs = materials.findByAcronym("eggs");
        final Material bread = materials.findByAcronym("bread");
        final Material oliveOil = materials.findByAcronym("oil");
        final Material sunflowerOil = materials.findByAcronym("so");

        final String batchNumber1 = "l1y17m5";
        final String batchNumber2 = "l2y17m5";

        register(yesterdayDinnerA, eggs, batchNumber1);
        register(yesterdayDinnerA, bread, batchNumber1);
        register(yesterdayDinnerB, eggs, batchNumber1);
        register(yesterdayDinnerB, oliveOil, batchNumber2);
        register(todayLunchA, oliveOil, batchNumber2);
        register(todayLunchA, eggs, batchNumber2);
        register(todayLunchB, bread, batchNumber1);
        register(todayLunchB, bread, batchNumber2);
        register(todayLunchB, sunflowerOil, batchNumber2);

        return false;
    }

    private void register(Meal meal, Material material, String batchnumber) {
        final RegisterLotsInMealController controller = new RegisterLotsInMealController();
        try {
            controller.registerMaterialUsed(meal, material, batchnumber);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MaterialUsedBootstraper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
