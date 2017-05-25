/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.domain.kitchen.MaterialUsed;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;
import eapli.util.DateTime;

/**
 *
 * @author João
 */
public class MaterialUsedPrinter implements Visitor<MaterialUsed>{

    @Override
    public void visit(MaterialUsed visitee) {
        System.out.printf("%-15s%-15s%-10s%-8s%-10s\n", DateTime.format(visitee.meal().getDate()), visitee.meal().dish().name(),
                visitee.meal().dish().dishType().acronym(), visitee.meal().mealType().mealType(), visitee.material().acronym());
    }
    
}
