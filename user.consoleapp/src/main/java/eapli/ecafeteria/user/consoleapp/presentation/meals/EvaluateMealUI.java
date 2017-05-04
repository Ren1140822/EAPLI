/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.EvaluateMealController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.user.consoleapp.presentation.booking.BookingPrinter;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sofia
 */
public class EvaluateMealUI extends AbstractUI {

    private final EvaluateMealController theController = new EvaluateMealController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<Booking> bookings = theController.listDeliveredBookings();
        if(bookings.iterator().hasNext()) {
            final SelectWidget<Booking> selector = new SelectWidget<>("Bookings:", bookings, new BookingPrinter());
            selector.show();
            final Booking chosenBooking = selector.selectedElement();
            
            int rating = Integer.parseInt(Console.readLine("Rating (1-5): "));
            while(rating < 1 && rating > 5) {
                rating = Integer.parseInt(Console.readLine("Rating (1-5): "));
            }
            
            final String comment = Console.readLine("Comment: ");
            
            try {
                theController.mealEvaluation(chosenBooking, rating, comment);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(EvaluateMealUI.class.getName()).log(Level.FINEST, null, ex);
            }
            
        } else {
            System.out.println("There are no previous bookings available to rate.");
        }
        System.out.println("Meal");
        return true;
    }

    @Override
    public String headline() {
        return "Evaluate Meal";
    }
}
