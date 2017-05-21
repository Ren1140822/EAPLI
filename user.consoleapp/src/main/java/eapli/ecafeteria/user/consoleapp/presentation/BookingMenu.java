/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation;

import eapli.ecafeteria.application.CafeteriaUserBaseController;
import eapli.ecafeteria.user.consoleapp.presentation.booking.CancelBookingAction;
import eapli.ecafeteria.user.consoleapp.presentation.booking.CreateBookingAction;
import eapli.ecafeteria.user.consoleapp.presentation.booking.ViewBookingsForNextDaysAction;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowMessageAction;

/**
 *
 * @author Meireles
 */
public class BookingMenu extends CafeteriaUserBaseUI {

    private static final int EXIT_OPTION = 0;
    private static final int LIST_MENUS_OPTION = 1;
    private static final int BOOK_A_MEAL_OPTION = 2;
    private static final int CANCEL_BOOKINGS_OPTION = 3;
    private static final int VIEW_UPCOMING_BOOKINGS_WITHIN_DAYS_OPTION = 4;

    private static final String MENU_TITLE = "Bookings";
    private static final String LIST_MENUS_TITLE = "List menus";
    private static final String BOOK_A_MEAL_TITLE = "Book a meal";
    private static final String CANCEL_BOOKINGS_TITLE = "Cancel a booking";
    private static final String VIEW_UPCOMING_BOOKINGS_WITHIN_DAYS_TITLE = "View upcoming bookings within N days";
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
        menu.add(new MenuItem(LIST_MENUS_OPTION, LIST_MENUS_TITLE, new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(BOOK_A_MEAL_OPTION, BOOK_A_MEAL_TITLE, new CreateBookingAction()));
        menu.add(new MenuItem(CANCEL_BOOKINGS_OPTION, CANCEL_BOOKINGS_TITLE, new CancelBookingAction()));
        menu.add(new MenuItem(VIEW_UPCOMING_BOOKINGS_WITHIN_DAYS_OPTION, VIEW_UPCOMING_BOOKINGS_WITHIN_DAYS_TITLE, new ViewBookingsForNextDaysAction()));
        menu.add(new MenuItem(EXIT_OPTION, EXIT_TITLE, new ReturnAction()));
        return menu;
    }

}
