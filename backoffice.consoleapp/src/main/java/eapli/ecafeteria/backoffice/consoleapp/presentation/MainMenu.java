/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.MyUserMenu;
import eapli.cafeteria.consoleapp.presentation.actions.ExitWithMessageAction;
import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.backoffice.consoleapp.presentation.actions.ActivateDeactivateDishTypeAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.actions.AddOrganicUnitAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.actions.AddUserAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.actions.ChangeDishTypeAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.actions.DeactivateUserAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.actions.ListDishTypeAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.actions.ListUsersAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.actions.RegisterDishTypeAction;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.ReturnAction;
import eapli.framework.actions.ShowMessageAction;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;

/**
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;

    // ORGANIC UNITS
    private static final int ADD_ORGANIC_UNIT_OPTION = 1;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;
    private static final int SET_USER_ALERT_LIMIT_OPTION = 2;

    // DISH TYPES
    private static final int DISH_TYPE_REGISTER_OPTION = 1;
    private static final int DISH_TYPE_LIST_OPTION = 2;
    private static final int DISH_TYPE_CHANGE_OPTION = 3;
    private static final int DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION = 4;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int ORGANIC_UNITS_OPTION = 3;
    private static final int SETTINGS_OPTION = 4;
    private static final int DISH_TYPES_OPTION = 5;

    public MainMenu() {
    }

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (AppSettings.instance().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu);
        } else {
            renderer = new VerticalMenuRenderer(menu);
        }
        return renderer.show();
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AppSettings.instance().session().authenticatedUser().id() + "]";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        if (!AppSettings.instance().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        if (AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.Administer)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.add(new SubMenu(USERS_OPTION, usersMenu, new ShowVerticalSubMenuAction(usersMenu)));
            final Menu organicUnitsMenu = buildOrganicUnitsMenu();
            mainMenu.add(new SubMenu(ORGANIC_UNITS_OPTION, organicUnitsMenu,
                    new ShowVerticalSubMenuAction(organicUnitsMenu)));
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.add(new SubMenu(SETTINGS_OPTION, settingsMenu, new ShowVerticalSubMenuAction(settingsMenu)));
        } else if (AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.ManageKitchen)) {
            // TODO
            throw new UnsupportedOperationException();
        } else if (AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.ManageMenus)) {
            final Menu myDishTypeMenu = buildDishTypeMenu();
            mainMenu.add(new SubMenu(DISH_TYPES_OPTION, myDishTypeMenu, new ShowVerticalSubMenuAction(myDishTypeMenu)));
        } else if (AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.Sale)) {
            // TODO
            throw new UnsupportedOperationException();
        }

        if (!AppSettings.instance().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.add(new MenuItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(SET_USER_ALERT_LIMIT_OPTION, "Set users' alert limit",
                new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildOrganicUnitsMenu() {
        final Menu menu = new Menu("Organic units >");

        menu.add(new MenuItem(ADD_ORGANIC_UNIT_OPTION, "Add Organic Unit", new AddOrganicUnitAction()));
        // TODO add other options for Organic Unit management
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.add(new MenuItem(ADD_USER_OPTION, "Add User", new AddUserAction()));
        menu.add(new MenuItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction()));
        menu.add(new MenuItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildDishTypeMenu() {
        final Menu menu = new Menu("Dish Type >");

        menu.add(new MenuItem(DISH_TYPE_REGISTER_OPTION, "Register new Dish Type", new RegisterDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_LIST_OPTION, "List all Dish Type", new ListDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_CHANGE_OPTION, "Change Dish Type description", new ChangeDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish Type",
                new ActivateDeactivateDishTypeAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    @Override
    protected Controller controller() {
        throw new UnsupportedOperationException("Menus don't have a controller");
    }
}
