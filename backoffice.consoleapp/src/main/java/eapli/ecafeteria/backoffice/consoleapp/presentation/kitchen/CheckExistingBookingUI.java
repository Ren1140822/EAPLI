/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CheckExistingBookingController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.*;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;
import java.util.Calendar;

/**
 *
 * @author João
 */
public class CheckExistingBookingUI extends AbstractUI {

    private final CheckExistingBookingController theController = new CheckExistingBookingController();

    protected Controller controller() {
        return this.theController;
    }

    @Override

    protected boolean doShow() {

        Calendar date = Console.readCalendar("dd-MM-yyyy");

        System.out.println("Select dish Type:");
        final Iterable<DishType> dishTypes = this.theController.listDishTypes();
        final SelectWidget<DishType> selector = new SelectWidget<>("Dish types:", dishTypes, new DishTypePrinter());
        selector.show();
        final DishType dishType = selector.selectedElement();

        String mealType;
        do {
            mealType = Console.readLine("Insert meal type (Lunch/Dinner/Both):");

        } while (!mealType.equalsIgnoreCase("Dinner")
                && !mealType.equalsIgnoreCase("Lunch")
                && !mealType.equalsIgnoreCase("Both"));

        Iterable<Booking> list = theController.checkBookingsByDateMealAndDishType(date, mealType, dishType);

        //    ListWidget<Booking> lister = new ListWidget<>("Bookings", list, new BookingPrinter());
        //   lister.show();
        for (Booking booking : list) {
            System.out.println(booking.meal().mealType().mealType());
            System.out.println(booking.meal().dish().dishType().acronym());
            System.out.println(booking.meal().getDate());
        }

        Console.waitForKey("Press Enter to return.");
        return true;
    }

    @Override
    public String headline() {
        return "Existing Bookings";
    }

}
