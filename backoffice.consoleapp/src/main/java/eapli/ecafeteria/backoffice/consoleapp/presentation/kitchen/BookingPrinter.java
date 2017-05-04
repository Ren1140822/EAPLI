/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author João
 */
 class BookingPrinter implements Visitor<Booking> {

    @Override
    public void visit(Booking visitee) {
	System.out.printf("%-10s%-30s\n", visitee.meal(), visitee.state());
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
