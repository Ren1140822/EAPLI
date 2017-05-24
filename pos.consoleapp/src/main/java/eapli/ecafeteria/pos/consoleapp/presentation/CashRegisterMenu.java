package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;

/**
 * Represents the cash register menu.
 * 
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
    //FIXME
    //Optional until further discussion with cliente
    private static final int REGISTER_COMPLAINT_OPTION = 3;
    
    private static final int CLOSE_CASH_REGISTER = 2;
    
    /**
     * Builds the cash register menu adding menu items.
     */
    private void buildCashRegisterMenu() {
        add(new MenuItem(OPEN_CASH_REGISTER, "Open Cash Register", new OpenCashRegisterAction()));
         add(new MenuItem(CLOSE_CASH_REGISTER, "Close Cash Register", new CloseCashRegisterAction()));
        add(new MenuItem(REGISTER_COMPLAINT_OPTION, "Register Complaint", new RegisterComplaintAction()));
        add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
    }
}
