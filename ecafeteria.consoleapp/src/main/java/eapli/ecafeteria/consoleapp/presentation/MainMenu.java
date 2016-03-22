/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.consoleapp.presentation;

import eapli.ecafeteria.presentation.LoginAction;
import eapli.ecafeteria.presentation.LogoutAction;
import eapli.framework.actions.ReturnAction;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.MenuSeparator;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

	private static final int EXIT_OPTION		  = 0;
	private static final int LOGIN_OPTION = 1;
	private static final int LOGOUT_OPTION = 2;
	private static final int LISTINGS_OPTION	  = 100;
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
		final Menu menu = buildMainMenu();
		final MenuRenderer renderer = new VerticalMenuRenderer(menu);
		return renderer.show();
	}

	@Override
	public String headline() {
		return "eCAFETERIA";
	}

	private Menu buildListingsMenu() {
		final Menu menu = new Menu("Listings...");

		// TODO add menu options
		menu.add(new MenuItem(EXIT_OPTION, "Return", new ReturnAction()));

		return menu;
	}

	private Menu buildMasterTablesMenu() {
		final Menu menu = new Menu("Master tables...");

		// TODO add menu options
		menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

		return menu;
	}

	private Menu buildMainMenu() {
		final Menu mainMenu = new Menu();

		// TODO add menu options
		mainMenu.add(new MenuSeparator());

		mainMenu.add(new MenuItem(LOGIN_OPTION, "Login",
				new LoginAction()));

		mainMenu.add(new MenuItem(LOGOUT_OPTION, "Logout",
				new LogoutAction()));

		final Menu listingsMenu = buildListingsMenu();
		mainMenu.add(new SubMenu(LISTINGS_OPTION, listingsMenu, new ShowVerticalSubMenuAction(listingsMenu)));

		final Menu masterMenu = buildMasterTablesMenu();
		mainMenu.add(new SubMenu(MASTER_TABLES_OPTION, masterMenu, new ShowVerticalSubMenuAction(masterMenu)));

		mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

		return mainMenu;
	}

	@Override
	protected Controller controller() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
