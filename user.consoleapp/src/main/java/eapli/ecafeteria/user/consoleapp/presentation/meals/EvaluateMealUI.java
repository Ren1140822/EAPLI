/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.EvaluateMealController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.Comment;
import eapli.ecafeteria.domain.meals.Rating;
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
 * It represents the UI of the MealEvaluation.
 *
 * @author Sofia Gonçalves [1150657@isep.ipp.pt] Pedro Chilro
 * [1150019@isep.ipp.pt]
 */
public class EvaluateMealUI extends AbstractUI {

    private final EvaluateMealController theController = new EvaluateMealController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        SelectWidget<Booking> selector;
        do {
            Iterable<Booking> bookings = theController.listDeliveredBookings();
            if (bookings.iterator().hasNext()) {
                selector = new SelectWidget<>("Bookings:", bookings, new BookingPrinter());
                selector.show();
                Booking chosenBooking = selector.selectedElement();
                if (chosenBooking != null) {
                    boolean flag = false;
                    Rating rate = null;
                    while (!flag) {
                        try {
                            int rating = Integer.parseInt(Console.readLine("Rating (1-5): "));
                            try {
                                rate = new Rating(rating);
                                flag = true;
                            } catch (IllegalStateException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Rating is with numbers.");
                        }
                    }
                    flag = false;
                    Comment commentary = null;
                    while (!flag) {
                        try {
                            String comment = Console.readLine("Comment: ");
                            commentary = new Comment(comment);
                            flag = true;
                        } catch (IllegalStateException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    try {
                        theController.mealEvaluation(chosenBooking, rate, commentary);
                    } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                        Logger.getLogger(EvaluateMealUI.class.getName()).log(Level.FINEST, null, ex);
                    }
                }
            } else {
                System.out.println("There are no previous bookings available to rate.");
                break;
            }
        } while (selector.selectedOption() != 0);
        return true;
    }

    @Override
    public String headline() {
        return "Evaluate Meal";
    }
}
