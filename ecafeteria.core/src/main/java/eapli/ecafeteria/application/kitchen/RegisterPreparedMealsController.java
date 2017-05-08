/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealsPreparedRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author Diogo Santos
 */
public class RegisterPreparedMealsController implements Controller {

    private final MealsPreparedRepository repository = PersistenceContext.repositories().mealsPrepared();
    private final ListMealService listMealsSvc = new ListMealService();

    public Iterable<Meal> findMeals() {

        //FIXME controllers must not have business logic
        //TODO check DateTime class in util library
        // today
        Calendar date = DateTime.now();
        return this.listMealsSvc.listMealsByUntilDateAndWithoutAmountOfMealsPrepared(date);
    }

    public MealsPrepared registerQuantityOfPreparedMeals(Meal meal, int quantity) throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        final MealsPrepared mealsPrepared = new MealsPrepared(meal, quantity);
        return repository.save(mealsPrepared);
    }
}
