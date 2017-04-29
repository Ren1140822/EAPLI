/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.RegisterMenuController;
import eapli.ecafeteria.persistence.*;
import eapli.framework.actions.Action;
import eapli.ecafeteria.domain.meals.*;
import eapli.framework.domain.Designation;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 *
 * @author Eduangelo Ferreira
 */
public class MenuBootstraper implements Action {

    @Override
    public boolean execute() {

        final DishRepository dishRepository = PersistenceContext.repositories().dishes();
        final Dish dishTofu = dishRepository.findByName(Designation.valueOf("tofu grelhado"));
        final MealType mealType = new MealType(MealType.MealTypes.ALMOCO);
        final Calendar start = Calendar.getInstance();
        final Calendar end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_MONTH, 5);
        final TimePeriod2 timePeriod = new TimePeriod2(start, end);
        register(dishTofu, mealType, timePeriod);
        return false;
    }

    /**
     *
     */
    private void register(Dish dish, MealType mealType, TimePeriod2 timePeriod) {

        final RegisterMenuController controller = new RegisterMenuController();

        try {
            controller.registerMenu(dish, mealType, timePeriod);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {

            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
