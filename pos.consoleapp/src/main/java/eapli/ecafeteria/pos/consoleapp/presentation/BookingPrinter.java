/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.visitor.Visitor;
import eapli.util.DateTime;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class BookingPrinter implements Visitor<Booking> {

    @Override
    public void visit(Booking visitee) {
        System.out.printf("%-11s%-8s%-30s%-10s\n",
                DateTime.format(visitee.meal().getDate()),
                visitee.meal().mealType().mealType(),
                visitee.meal().dish().name(), 
                visitee.meal().dish().currentPrice());
    }

}
