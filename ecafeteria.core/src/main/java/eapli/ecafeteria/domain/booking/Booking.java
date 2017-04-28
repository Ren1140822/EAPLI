/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.AggregateRoot;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

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

    @OneToMany(cascade = CascadeType.MERGE)
    private CafeteriaUser user;

    @OneToMany(cascade = CascadeType.MERGE)
    private Meal meal;

    private BookingState state;

    public Booking() {
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
    
    public boolean belongsTo(CafeteriaUser user){
        return this.user.equals(user);
    }
    
    public boolean isAtState(BookingState state){
        return this.state.equals(state);
    }

}
