/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.actions.ExitWithMessageAction;
import eapli.cafeteria.consoleapp.presentation.actions.LoginAction;
import eapli.cafeteria.consoleapp.presentation.actions.LogoutAction;
import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.utente.consoleapp.presentation.actions.ListDishTypeAction;
import eapli.framework.actions.ReturnAction;
import eapli.framework.actions.ShowMessageAction;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
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

	private static final int EXIT_OPTION			= 0;

        
        // DISH TYPE
	private static final int LIST_DISH_TYPE_OPTION		= 4;
        
	// MY USER
	private static final int CHANGE_PASSWORD_OPTION	        = 1;
	private static final int LOGIN_OPTION			= 2;
	private static final int LOGOUT_OPTION			= 3;

	// MAIN MENU
	private static final int MY_USER_OPTION			= 1;
        private static final int DISH_TYPE_OPTION		= 2;

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
		final MenuRenderer renderer = new VerticalMenuRenderer(menu);
		return renderer.show();
	}

	@Override
	public String headline() {
		return "eCAFETERIA [@" + AppSettings.instance().session().authenticatedUser().id() + "]";
	}

	private Menu buildMyUserMenu() {
		final Menu myUserMenu = new Menu("My account >");

		myUserMenu.add(
		        new MenuItem(CHANGE_PASSWORD_OPTION, "Change password", new ShowMessageAction("Not implemented yet")));
		myUserMenu.add(new MenuItem(LOGIN_OPTION, "Change user (Login)", new LoginAction()));
		myUserMenu.add(new MenuItem(LOGOUT_OPTION, "Logout", new LogoutAction()));

		return myUserMenu;
	}

	private Menu buildMainMenu() {
		final Menu mainMenu = new Menu();

		final Menu myUserMenu = buildMyUserMenu();
		mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

		mainMenu.add(new VerticalSeparator());

                final Menu dishTypeMenu = buildDishTypeMenu();
		mainMenu.add(new SubMenu(DISH_TYPE_OPTION, dishTypeMenu, new ShowVerticalSubMenuAction(dishTypeMenu)));
		// TODO add menu options

		mainMenu.add(new VerticalSeparator());

		mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

		return mainMenu;
	}
        
        private Menu buildDishTypeMenu() {
		final Menu dishTypeMenu = new Menu("Dish Type >");

		dishTypeMenu.add(new MenuItem(LIST_DISH_TYPE_OPTION, "List Dish Type", new ListDishTypeAction()));
		
		// TODO add menu options
		dishTypeMenu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

		return dishTypeMenu;
	}

	private Menu buildXptoMenu() {
		final Menu menu = new Menu("Xpto >");

		// TODO add menu options
		menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

		return menu;
	}

	@Override
	protected Controller controller() {
		throw new UnsupportedOperationException("Menus don't have a controller");
	}
}
