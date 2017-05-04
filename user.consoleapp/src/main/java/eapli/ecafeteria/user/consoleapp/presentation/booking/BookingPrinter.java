/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class BookingPrinter implements Visitor<Booking> {

    @Override
    public void visit(Booking visitee) {
        System.out.printf("%-10s%-10s%-10s%-10s%-10s\n", visitee.meal().pk(), visitee.meal().mealType(),
                visitee.meal().dish(), visitee.meal().timePeriod(), String.valueOf(visitee.state()));
    }
    
}
