/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author PC
 */
public class MealPrinter implements Visitor<Meal>{

    @Override
    public void visit(Meal visitee) {
       System.out.println(visitee.mealType().mealType()+" - "+visitee.dish().name());
    }
    
}
