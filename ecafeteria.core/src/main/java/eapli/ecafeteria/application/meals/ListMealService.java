/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

import java.util.Calendar;

/**
 * an application service to avoid code duplication.
 */
public class ListMealService {

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    public Iterable<Meal> listMealsByDate(Calendar date) {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.mealRepository.findByDate(date);
    }

    public Iterable<Meal> listMealsByUntilDateAndWithoutAmountOfMealsPrepared(Calendar date) {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.mealRepository.findByUntilDateAndWithoutAmountOfMealsPrepared(date);
    }

    public Iterable<Meal> listMealsByDateAndMealType(Calendar date, MealType type) {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.mealRepository.findByDateAndMealType(date, type);
    }
    

}
