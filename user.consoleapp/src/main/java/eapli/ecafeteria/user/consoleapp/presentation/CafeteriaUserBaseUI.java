/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.CafeteriaUserBaseController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;
import eapli.util.DateTime;

/**
 *
 * @author mcn
 */
public abstract class CafeteriaUserBaseUI extends AbstractUI {

    protected abstract CafeteriaUserBaseController controller();

    public String id() {
        return controller().id().toString();
    }

    public String balance() {
        return "CURRENT BALANCE OF YOUR USERCARD: " + controller().balance().toString();
    }

    public String nextBooking() {
        Booking nextBooking = this.controller().nextBooking();
        String booking;

        if (nextBooking == null) {
            booking = "There are no bookings.";
        } else {
            booking = String.format("%-11s%-7s%-30s", DateTime.format(nextBooking.meal().getDate()),
                    nextBooking.meal().mealType().mealType(),
                    nextBooking.meal().dish().name());
        }
        return "NEXT ACTIVE BOOKING: " + booking;
    }

    @Override
    public String headline() {
        StringBuilder headline = new StringBuilder();
        headline.append(" eCAFETERIA [@");
        headline.append(id());
        headline.append("] ");
        int start = headline.length() + 3;
        int end = BORDER.length();
        if (start < end) {
            headline.append(BORDER.substring(start, end));
        }
        headline.append(System.lineSeparator());
        headline.append(System.lineSeparator());
        headline.append(balance());
        headline.append(System.lineSeparator());
        headline.append(System.lineSeparator());
        headline.append(nextBooking());
        headline.append(System.lineSeparator());
        return headline.toString();
    }

    @Override
    protected void drawFormTitle(String title) {
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }

    /**
     * It provides a vertical or horizontal menu renderer depending on the current properties configuration.
     * 
     * @param menu The menu to be rendered.
     * @return It returns the matching menu renderer.
     */
    protected MenuRenderer chooseRendererFor(Menu menu) {
        return (Application.settings().isMenuLayoutHorizontal()) ? new HorizontalMenuRenderer(menu) : new VerticalMenuRenderer(menu);
    }

    /**
     * It inserts a vertical separator if the menu is to be rendered vertically or nothing if horizontal.
     * 
     * @param menu The menu to be rendered.
     */
    protected void separatorFor(Menu menu) {
        if (!Application.settings().isMenuLayoutHorizontal()) {
            menu.add(new VerticalSeparator());
        }
    }

}
