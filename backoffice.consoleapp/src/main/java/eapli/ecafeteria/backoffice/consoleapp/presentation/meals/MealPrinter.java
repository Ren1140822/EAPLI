/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt] Diogo Santos [1150451@isep.ipp.pt]
 */
public class MealPrinter implements Visitor<Meal>{

    @Override
    public void visit(Meal visitee) {
        System.out.printf("%-20%-15s%-10s%-4s\n", visitee.timePeriod(), visitee.dish().name(),
                visitee.dish().dishType(), visitee.mealType().mealType());
    }
    
}
