/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.PublishMenuController;
import eapli.ecafeteria.application.meals.RegisterMenuController;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.persistence.*;
import eapli.framework.actions.Action;
import eapli.ecafeteria.domain.meals.*;
import eapli.framework.domain.Designation;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author Eduangelo Ferreira
 */
public class MenuBootstraper implements Action {

    @Override
    public boolean execute() {
        final OrganicUnitRepository organicUnitRepository = PersistenceContext.repositories().organicUnits();
        final OrganicUnit organicUnit = organicUnitRepository.findByAcronym("ISEP");

        final DishRepository dishes = PersistenceContext.repositories().dishes();
        final Dish dish1 = dishes.findByName(Designation.valueOf("Chop Sausage"));
        final Dish dish2 = dishes.findByName(Designation.valueOf("Grilled Tofu"));
        final Dish dish3 = dishes.findByName(Designation.valueOf("Filet Steak"));

        final MealType lunch = new MealType(MealType.MealTypes.LUNCH);
        final MealType dinner = new MealType(MealType.MealTypes.DINNER);

        final Calendar yesterday = DateTime.yesterday();
        final Calendar today = DateTime.now();
        final Calendar tomorrow = DateTime.tomorrow();
        final TimePeriod2 timePeriod = new TimePeriod2(yesterday, tomorrow);

        final Set<Meal> meals = new HashSet<>();
        meals.add(new Meal(dish1, dinner, yesterday));
        meals.add(new Meal(dish2, dinner, yesterday));
        meals.add(new Meal(dish1, lunch, today));
        meals.add(new Meal(dish3, lunch, today));
        meals.add(new Meal(dish1, dinner, today));
        meals.add(new Meal(dish2, dinner, today));
        meals.add(new Meal(dish2, lunch, tomorrow));
        meals.add(new Meal(dish3, lunch, tomorrow));

        Menu aMenu = register(meals, timePeriod, organicUnit);
        publish(aMenu);
        return false;
    }

    /**
     *
     */
    private Menu register(Set<Meal> meals, TimePeriod2 timePeriod, OrganicUnit organicUnit) {

        final RegisterMenuController controller = new RegisterMenuController();

        Menu thisMenu = null;
        try {
            thisMenu = controller.registerMenu(meals, organicUnit, timePeriod);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {

            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
        return thisMenu;
    }

    private void publish(Menu menu) {
        final PublishMenuController controller = new PublishMenuController();

        try {
            controller.publishMenu(menu);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {

            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
