package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class CashRegisterMenu extends Menu {

    public CashRegisterMenu() {
        super("Cash Register >");
        buildCashRegisterMenu();
    }

    private static final int EXIT_OPTION = 0;

    // CASH REGISTER SUB MENU
    private static final int OPEN_CASH_REGISTER = 1;

    private void buildCashRegisterMenu() {
        add(new MenuItem(OPEN_CASH_REGISTER, "Open Cash Register", new OpenCashRegisterAction()));
        add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
    }
}
