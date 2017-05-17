/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.booking;

import eapli.ecafeteria.application.booking.ViewBookingsForNextDaysController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.util.io.Console;

/**
 *
 * 
 * @author Meireles
 */
public class ViewBookingsForNextDaysUI extends AbstractUI {

    private final ViewBookingsForNextDaysController theController = new ViewBookingsForNextDaysController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        int days = Integer.MIN_VALUE;
        do{
            String answer = Console.readLine("How many upcoming days? ");
            try{
                days = Integer.parseInt(answer);
                if(days<0){
                    System.out.println("The number of days must be positive.");
                }
            } catch(NumberFormatException e){
                System.out.println("Please insert a valid number.");
            }
        } while(days<0);
        Iterable<Booking> list = theController.listBookingsOfNext(days);
        ListWidget<Booking> lister = new ListWidget<>("Bookings", list, new BookingPrinter());
        lister.show();
        if(!list.iterator().hasNext()){
            System.out.println("There are no upcoming bookings.");
        }
        Console.waitForKey("Press Enter to return.");
        return true;
    }

    @Override
    public String headline() {
        return "Upcoming Bookings";
    }
    
}
