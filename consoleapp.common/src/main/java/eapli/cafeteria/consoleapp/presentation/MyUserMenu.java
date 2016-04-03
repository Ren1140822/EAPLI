package eapli.cafeteria.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.actions.LoginAction;
import eapli.cafeteria.consoleapp.presentation.actions.LogoutAction;
import eapli.framework.actions.ShowMessageAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;

public class MyUserMenu extends Menu {

    // MY USER
    private static final int CHANGE_PASSWORD_OPTION = 1;
    private static final int LOGIN_OPTION = 2;
    private static final int LOGOUT_OPTION = 3;

    public MyUserMenu() {
        super("My account >");
        buildMyUserMenu();
    }

    private void buildMyUserMenu() {
        add(new MenuItem(CHANGE_PASSWORD_OPTION, "Change password", new ShowMessageAction("Not implemented yet")));
        add(new MenuItem(LOGIN_OPTION, "Change user (Login)", new LoginAction()));
        add(new MenuItem(LOGOUT_OPTION, "Logout", new LogoutAction()));
    }
}
