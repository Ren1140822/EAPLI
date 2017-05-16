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
import eapli.util.DateTime;

/**
 *
 * @author mcn
 */
public abstract class CafeteriaUserBaseUI extends AbstractUI {

    protected abstract CafeteriaUserBaseController controller();

    public String showBalance() {
        return "CURRENT BALANCE OF YOUR USERCARD: " + "\n" + controller().balance().toString();
    }

    public String showNextBooking() {
        Booking nextBooking = this.controller().nextBooking();
        String booking;

        if (nextBooking == null) {
            booking = "There are no bookings.";
        } else {
            booking = String.format("\n%-15s%-10s%-30s\n", DateTime.format(nextBooking.meal().getDate()),
                    nextBooking.meal().mealType().mealType(),
                    nextBooking.meal().dish().name());
        }
        return "NEXT ACTIVE BOOKING: " + booking;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + Application.session().session().authenticatedUser().id() + "]   " + "\n\n" + showBalance() + "\n\n"
                + showNextBooking();
    }

    @Override
    protected void drawFormTitle(String title) {
        // drawFormBorder();
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }
}
