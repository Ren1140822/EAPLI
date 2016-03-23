/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation;

import eapli.ecafeteria.backoffice.presentation.AddUserAction;
import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.users.ActionRight;
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
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

	private static final int EXIT_OPTION		  = 0;

	//MY USER
	private static final int CHANGE_PASSWORD_OPTION			= 1;
	private static final int LOGIN_OPTION = 2;
	private static final int LOGOUT_OPTION = 3;

	//private static final int LISTINGS_OPTION	  = 100;
	//private static final int MASTER_TABLES_OPTION = 200;
	private static final int ADD_USER_OPTION = 1;

	// USERS

	// ORGANIC UNITS

	// SETTINGS
	private static final int SET_KITCHEN_ALERT_LIMIT_OPTION	= 1;
	private static final int SET_USER_ALERT_LIMIT_OPTION	= 2;

	// MAIN MENU
	private static final int MY_USER_OPTION					= 1;
	private static final int USERS_OPTION					= 2;
	private static final int ORGANIC_UNITS_OPTION			= 3;
	private static final int SETTINGS_OPTION				= 4;

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
		} else if (AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.ManageMenus)) {
			// TODO
		} else if (AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.Sale)) {
			// TODO
		}
		mainMenu.add(new VerticalSeparator());

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

		// TODO add menu options
		menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

		return menu;
	}

	private Menu buildUsersMenu() {
		final Menu menu = new Menu("Users >");

		menu.add(new MenuItem(ADD_USER_OPTION, "Add User", new AddUserAction()));


		// TODO add menu options
		menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

		return menu;
	}

	@Override
	protected Controller controller() {
		throw new UnsupportedOperationException("Menus don't have a controller");
	}
}
