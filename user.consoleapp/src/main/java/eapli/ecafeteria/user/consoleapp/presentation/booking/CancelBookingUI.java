/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.booking;

import eapli.ecafeteria.application.booking.CancelBookingController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class CancelBookingUI extends AbstractUI {

    private final CancelBookingController theController = new CancelBookingController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        Booking updtBooking;
        BalanceAlertUI balanceAlertUI = new BalanceAlertUI();
        do {
            Iterable<Booking> bookingsDone = this.theController.listBookings();
            SelectWidget<Booking> selector = new SelectWidget<>("Bookings:", bookingsDone, new BookingPrinter());
            selector.show();

            updtBooking = selector.selectedElement();
            if (updtBooking != null) {
                try {
                    this.theController.cancel(updtBooking);
                    //The alert
                    balanceAlertUI.doShow();
                } catch (DataConcurrencyException ex) {
                    System.out.println("The booking has suffered some changes and it was not possible to cancel. Please try again.");
                } catch (DataIntegrityViolationException ex) {
                    //should not happen!
                    Logger.getLogger(CancelBookingUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } while (updtBooking != null);
        return false;
    }

    @Override
    public String headline() {
        return "Cancel Booking";
    }

}
