/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation;

import eapli.ecafeteria.application.CafeteriaUserBaseController;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowMessageAction;

/**
 *
 * @author Meireles
 */
public class AccountMenu  extends CafeteriaUserBaseUI {

    private static final int EXIT_OPTION = 0;
    private static final int LIST_MOVEMENTS_OPTION = 1;

    private static final String MENU_TITLE = "Account";
    private static final String LIST_MOVEMENTS_TITLE = "List movements";
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
        menu.add(new MenuItem(LIST_MOVEMENTS_OPTION, LIST_MOVEMENTS_TITLE, new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(EXIT_OPTION, EXIT_TITLE, new ReturnAction()));
        return menu;
    }

}
