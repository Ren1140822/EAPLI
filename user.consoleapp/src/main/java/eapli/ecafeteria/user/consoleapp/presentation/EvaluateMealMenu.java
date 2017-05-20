/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation;

import eapli.ecafeteria.application.CafeteriaUserBaseController;
import eapli.ecafeteria.user.consoleapp.presentation.meals.EvaluateMealAction;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;

/**
 *
 * @author Meireles
 */
public class EvaluateMealMenu extends CafeteriaUserBaseUI {

    private static final int EXIT_OPTION = 0;
    private static final int CHOOSE_BOOKING_TO_EVALUATE_OPTION = 1;

    private static final String MENU_TITLE = "Evaluate Meal";
    private static final String CHOOSE_BOOKING_TO_EVALUATE_TITLE = "Choose meal to evaluate";
    private static final String EXIT_TITLE = "Return";

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

    @Override
    public boolean show() {
        return doShow();
    }

    @Override
    protected boolean doShow() {
        boolean wantsToExit = false;
        do {
            drawFormTitle();
            final Menu menu = buildMenu();
            final MenuRenderer renderer = chooseRendererFor(menu);
            System.out.println("\n>> " + menu.title());
            wantsToExit = renderer.show();
        } while (!wantsToExit);
        return wantsToExit;
    }

    private Menu buildMenu() {
        final Menu menu = new Menu(MENU_TITLE);
        menu.add(new MenuItem(CHOOSE_BOOKING_TO_EVALUATE_OPTION, CHOOSE_BOOKING_TO_EVALUATE_TITLE, new EvaluateMealAction()));
        menu.add(new MenuItem(EXIT_OPTION, EXIT_TITLE, new ReturnAction()));
        return menu;
    }

    
}
