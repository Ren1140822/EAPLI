/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.EvaluateMealController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Sofia
 */
public class EvaluateMealUI extends AbstractUI {

    private final EvaluateMealController theController = new EvaluateMealController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        //TO DO, implementation 
        System.out.println("Meal");
        return true;
    }

    @Override
    public String headline() {
        return "Evaluate Meal";
    }
}
