/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.booking;

import eapli.ecafeteria.application.booking.CreateBookingController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.DateTime;
import eapli.util.io.Console;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author nunopinto
 */
public class CreateBookingUI extends AbstractUI {

    private final CreateBookingController controller = new CreateBookingController();

    @Override
    protected boolean doShow() {
        String dayToBook;
        do {
            dayToBook = Console.readLine("Insert the date (YYYY-MM-DD):");
        } while (!validateInputDate(dayToBook));

        System.out.println("NOT SUPPORTED YET");

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
