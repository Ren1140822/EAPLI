/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

/**
 *
 * @author Miguel Silva - 1150901
 */
public interface BookingState {

    boolean transitToCanceled();

    boolean transitToDefinitive();

    boolean transitToDelivered();

    boolean transitToDone();

    boolean transitToWasted();
}
