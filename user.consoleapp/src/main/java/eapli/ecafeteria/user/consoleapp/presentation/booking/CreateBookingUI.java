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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nunopinto
 */
public class CreateBookingUI extends AbstractUI {

    private final CreateBookingController controller = new CreateBookingController();

    @Override
    protected boolean doShow() {
        Date dayToBook;
        do {

            dayToBook = Console.readDate("Insert the date(YYYY/MM/DD):");

        } while (!validateInputDate(dayToBook));
        
        
        
        List<Meal> meals = controller.menusOfDay(dayToBook);
        
        if(meals.isEmpty()){
            System.out.println("There are no availables meals to book on the given day!");
            return true;
        }
        
        Meal choosedMeal;
        SelectWidget<Meal> selector = new SelectWidget<>("Meals:", meals, new MealPrinter());
        selector.show();
        choosedMeal = selector.selectedElement();
        if (choosedMeal == null) {
            return true;
        }
        
        if(!controller.hasEnoughMoney( choosedMeal)){
            System.out.println("");
            System.out.println("Not enough money to book");
            System.out.println("");
            return true;
        }
     
        try {
            controller.save(choosedMeal);
        } catch (DataConcurrencyException ex) {
            System.out.println("The meal has suffered some changes and it was not possible to book. Please try again.");
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(CreateBookingUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("");
        System.out.println("Meal booked!");
        System.out.println("");
        return false;
    }

    @Override
    public String headline() {
        return "Create Booking";
    }

    public boolean validateInputDate(Date dayToBook) {

        Calendar cal = DateTime.dateToCalendar(dayToBook);
        if (DateTime.isBeforeToday(cal)) {
            System.out.println("");
            System.out.println("Invalid Date!");
            System.out.println("");
            return false;
        }
        return true;

    }

}
