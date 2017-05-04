package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.framework.actions.ReturnAction;
import eapli.framework.actions.ShowMessageAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class SalesMenu extends Menu {

    private static final int EXIT_OPTION = 0;

    // SALES SUB MENU
    private static final int REGISTER_MEAL_DELIVERY = 1;
    private static final int TOPUP_CARD = 2;


    public SalesMenu() {
        super("Sales >");
        buildSalesMenu();
    }

    private void buildSalesMenu() {
        add(new MenuItem(REGISTER_MEAL_DELIVERY, "Register meal delivery", new ShowMessageAction("Not implemented yet")));
        add(new MenuItem(TOPUP_CARD, "TopUp Card", new TopUpAccountCardAction()));
        add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
    }
}
