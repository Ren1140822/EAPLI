/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.visitor.Visitor;
import eapli.util.DateTime;

/**
 *
 * @author João
 */
 class BookingPrinter implements Visitor<Booking> {

    @Override
    public void visit(Booking visitee) {
	System.out.printf("%-15s%-15s%-10s%-4s\n", visitee.meal().dish().name(), DateTime.format(visitee.meal().getDate()), visitee.state(), visitee.meal().mealType().mealType());
    }

    @Override
    public void beforeVisiting(Booking visitee) {
	// nothing to do
    }

    @Override
    public void afterVisiting(Booking visitee) {
	// nothing to do
    }
}
