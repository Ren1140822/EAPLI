/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Nuno Pinto [1150838@isep.ipp.pt] Henrique Oliveira
 * [1150738@isep.ipp.pt]
 */
@Entity
public class Booking implements Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long pk;

    @ManyToOne(cascade = CascadeType.MERGE)
    private CafeteriaUser user;

    @OneToOne(cascade = CascadeType.MERGE)
    private Meal meal;
    private BookingState state;

    protected Booking() {
        // for ORM
    }

    public Booking(CafeteriaUser user, Meal meal, BookingState actualState) {
        if (user == null || meal == null || actualState == null) {
            throw new IllegalStateException();
        }
        this.user = user;
        this.meal = meal;
        this.state = actualState;
    }

    public void cancel() {
        if (this.state != BookingState.DONE) {
            throw new IllegalStateException();
        }
        this.state = BookingState.CANCELED;
    }

    public boolean belongsTo(CafeteriaUser user) {
        return this.user.equals(user);
    }
    
    public boolean isOfMeal(Meal meal) {
        return this.meal.equals(meal);
    }

    public boolean isAtState(BookingState state) {
        return this.state.equals(state);
    }
    
    public void deliver() {
        if (this.state != BookingState.DEFINITIVE) {
            throw new IllegalStateException();
        }
        this.state = BookingState.DELIVERED;
    }
    
    public Meal meal(){
        return this.meal;
    }
    
    public BookingState state(){
        return this.state;
    }

}
