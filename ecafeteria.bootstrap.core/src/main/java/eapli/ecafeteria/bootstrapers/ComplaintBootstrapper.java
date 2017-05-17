/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cashregister.RegisterComplaintController;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Santos
 */
public class ComplaintBootstrapper implements Action {

    @Override
    public boolean execute() {
        final DishTypeRepository dishTypeRepo = PersistenceContext.repositories().dishTypes();
        final DishType vegie = dishTypeRepo.findByAcronym("vegie");
        final Dish dish = new Dish(vegie, Designation.valueOf("Summer Salad"), Money.euros(55.0));
        final MecanographicNumber number = new MecanographicNumber("11111");
        register("I did not like the dish!", dish, number);
        return false;
    }

    private void register(String complaint, Dish dish, MecanographicNumber number) {
        final RegisterComplaintController controller = new RegisterComplaintController();
        try {
            controller.insertComplaint(complaint);
            controller.insertDish(dish);
            controller.insertMecanograficNumber(number);
            controller.saveComplaint();
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
