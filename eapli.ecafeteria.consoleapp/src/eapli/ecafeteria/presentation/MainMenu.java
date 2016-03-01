/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.presentation;

import eapli.framework.Controller;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.AbstractUI;
import eapli.framework.presentation.Menu;
import eapli.framework.presentation.MenuItem;
import eapli.framework.presentation.MenuSeparator;
import eapli.framework.presentation.SubMenu;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;
    private static final int LISTINGS_OPTION = 100;
    private static final int MASTER_TABLES_OPTION = 200;

    public MainMenu() {
    }

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     *
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        Menu menu = buildMainMenu();
        return menu.show();
    }

    @Override
    public String headline() {
        return "eCAFETERIA";
    }

    private Menu buildListingsMenu() {
        Menu menu = new Menu("Listings...");

        //TODO add menu options
        menu.addMenuItem(new MenuItem(EXIT_OPTION, "Return", new ReturnAction()));

        return menu;
    }

    private Menu buildMasterTablesMenu() {
        Menu menu = new Menu("Master tables...");

        //TODO add menu options
        menu.addMenuItem(new MenuItem(EXIT_OPTION, "Return ",
                new ReturnAction()));

        return menu;
    }

    private Menu buildMainMenu() {
        Menu menu = new Menu();

        //TODO add menu options
        menu.addMenuItem(new MenuSeparator());

        //TODO add menu options
        menu.addMenuItem(new MenuSeparator());

        menu.addMenuItem(new SubMenu(LISTINGS_OPTION, buildListingsMenu()));

        menu.addMenuItem(new SubMenu(MASTER_TABLES_OPTION,
                buildMasterTablesMenu()));

        menu.addMenuItem(new MenuItem(EXIT_OPTION, "Exit",
                new ExitWithMessageAction()));

        return menu;
    }

    @Override
    protected Controller controller() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
