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
public abstract class BookingStateAbstract implements BookingState {
    
    public boolean transitToCanceled(){
        return false;
    }
    
    public boolean transitToDefinitive(){
        return false;
    }
    
    public boolean transitToDelivered(){
        return false;
    }
    
    public boolean transitToDone(){
        return false;
    }
    
    public boolean transitToWasted(){
        return false;
    }
}
