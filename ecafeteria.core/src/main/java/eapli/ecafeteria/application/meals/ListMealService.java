/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.TimePeriod2;

import java.util.Calendar;

/**
 *
 * @author Sofia Silva
 */
public class ListMealService {
    
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    
    public Iterable<Meal> listMealsByDate(Calendar date) {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.mealRepository.findByDate(date);
    }
    
}
