/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.ExitWithMessageAction;
import eapli.cafeteria.consoleapp.presentation.MyUserMenu;
import eapli.ecafeteria.application.CafeteriaUserBaseController;
import eapli.framework.actions.Action;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowSubMenuAction;
import eapli.framework.presentation.console.ShowUiAction;
import eapli.framework.presentation.console.SubMenu;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends CafeteriaUserBaseUI {

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int BOOKINGS_OPTION = 2;
    private static final int EVALUATE_MEAL_OPTION = 3;
    private static final int ACCOUNT_OPTION = 4;

    private static final String BOOKINGS_TITLE = "Bookings";
    private static final String EVALUATE_MEAL_TITLE = "Evaluate Meals";
    private static final String ACCOUNT_TITLE = "Account";
    private static final String EXIT_TITLE = "Rxit";

    @Override
    public boolean show() {
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        boolean wantsToExit = false;
        do {
            drawFormTitle();
            final Menu menu = buildMainMenu();
            final MenuRenderer renderer = chooseRendererFor(menu);
            wantsToExit = renderer.show();
        } while (!wantsToExit);
        return wantsToExit;
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, showActionFor(myUserMenu)));

        separatorFor(mainMenu);

        mainMenu.add(new MenuItem(BOOKINGS_OPTION, BOOKINGS_TITLE, new ShowUiAction(new BookingMenu())));

        separatorFor(mainMenu);

        mainMenu.add(new MenuItem(EVALUATE_MEAL_OPTION, EVALUATE_MEAL_TITLE, new ShowUiAction(new EvaluateMealMenu())));

        separatorFor(mainMenu);

        mainMenu.add(new MenuItem(ACCOUNT_OPTION, ACCOUNT_TITLE, new ShowUiAction(new AccountMenu())));
        
        // TODO add menu options

        separatorFor(mainMenu);

        mainMenu.add(new MenuItem(EXIT_OPTION, EXIT_TITLE, new ExitWithMessageAction()));

        return mainMenu;
    }

    /**
     * It provides the action to show the menu.
     * 
     * @param menu The menu to be shown.
     * @return It returns the action to show the menu.
     */
    private Action showActionFor(Menu menu){
        return new ShowSubMenuAction(menu,chooseRendererFor(menu));
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }
}
