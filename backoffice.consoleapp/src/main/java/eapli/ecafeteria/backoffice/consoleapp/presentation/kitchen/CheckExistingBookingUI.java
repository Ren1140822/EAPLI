/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CheckExistingBookingController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author João
 */
public class CheckExistingBookingUI extends AbstractListUI<Booking> {

    private final CheckExistingBookingController theController = new CheckExistingBookingController();

    protected Controller controller() {
	return this.theController;
    }
    
    @Override
    protected Iterable<Booking> listOfElements() {
       return this.theController.checkBookingsByDateMealAndDishType();
    }

    @Override
    protected Visitor<Booking> elementPrinter() {
        return new BookingPrinter();
    }

    @Override
    protected String elementName() {
        return "Booking";
    }
    
}
