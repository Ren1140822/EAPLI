/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.booking;

import eapli.ecafeteria.application.booking.CreateBookingController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.user.consoleapp.presentation.meals.MealPrinter;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.DateTime;
import eapli.util.io.Console;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nunopinto
 */
public class CreateBookingUI extends AbstractUI {

    private final CreateBookingController controller = new CreateBookingController();
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    @Override
    protected boolean doShow() {
        String dayToBook;
        do {
            dayToBook = Console.readLine("Insert the date (YYYY-MM-DD):");
        } while (!validateInputDate(dayToBook));
        System.out.println("");
        List<Meal> meals = controller.menusOfDay(controller.transformDate(dayToBook));
        Meal choosedMeal;
        SelectWidget<Meal> selector = new SelectWidget<>("Meals:", meals, new MealPrinter());
        selector.show();
        choosedMeal = selector.selectedElement();
        if(choosedMeal==null) return true;
        try {
            controller.book(choosedMeal);
        } catch (DataConcurrencyException ex) {
             System.out.println("The meal has suffered some changes and it was not possible to book. Please try again.");
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(CreateBookingUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
        System.out.println("Meal booked!");
        System.out.println("");
        return true;
    }

    @Override
    public String headline() {
        return "Create Booking";
    }

    public boolean validateInputDate(String dayToBook) {
        int year, month, day;
        String tokens[] = dayToBook.split("-");
        if (tokens.length != 3) {
            return false;
        }

        try {
            year = Integer.parseInt(tokens[0]);
            month = Integer.parseInt(tokens[1]);
            day = Integer.parseInt(tokens[2]);
        } catch (NumberFormatException ex) {
            return false;
        }

        try {
            Calendar cal = DateTime.newCalendar(year, month, day);
            cal.setLenient(false);
            cal.getTime();
        } catch (Exception e) {
            System.out.println("Invalid Date!");
            return false;
        }
        return true;
    }

}
