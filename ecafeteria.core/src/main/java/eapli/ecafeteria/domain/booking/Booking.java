/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.account.Refund;
import eapli.ecafeteria.domain.cafeteria.account.RefundBuilder;
import eapli.ecafeteria.domain.meals.Meal;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 * @TODO is this an Entity, a value object, an aggregate?
 * @FIXME javadoc
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

    @ManyToOne(cascade = CascadeType.MERGE)
    private Meal meal;
    private BookingState state;

    protected Booking() {
        // for ORM
    }

    public Booking(CafeteriaUser user, Meal meal) {
        if (user == null || meal == null) {
            throw new IllegalStateException();
        }
        
        this.user = user;
        this.meal = meal;
        this.state = BookingState.DONE;
    }

    /**
     * It cancels the booking by changing its state from "Done" to "Canceled"
     * and provides the refunding. It throws an IllegalStateException if the
     * booking is in a non-cancellable state.
     *
     * @return It returns the corresponding refund.
     */
    public Refund cancel() {
        if (this.state != BookingState.DONE) {
            throw new IllegalStateException();
        }
        this.state = BookingState.CANCELED;
        RefundBuilder refund = new RefundBuilder();
        refund.withPenalty(meal.getDate(), meal.mealType(), Calendar.getInstance());
        refund.withMecanographicNumber(user.mecanographicNumber());
        refund.withMoney(meal.dish().currentPrice());
        return refund.build();
    }

    public boolean belongsTo(CafeteriaUser user) {
        return this.user.equals(user);
    }

    public boolean isOfMeal(Meal meal) {
        return this.meal.equals(meal);
    }

    /**
     * It checks if the booking is currently at a certain state.
     *
     * @param state The state to be compared to.
     * @return It returns "true" if the booking is currently at state indicated
     * by the parameter or "false" otherwise.
     */
    public boolean isAtState(BookingState state) {
        return this.state.equals(state);
    }

    /**
     * It checks if the booking's meal occurs until a certain date.
     *
     * @param date The limit date.
     * @return It returns "true" if the meal date occurs before or at the same
     * day as the limit date or "false" otherwise.
     */
    public boolean isUntilDate(Calendar date) {
        return meal.getDate().before(date) || meal.getDate().equals(date);
    }

    /**
     * It makes the booking definitive by changing it state from "Done" to
     * "Definitive". A booking is considered to be definitive when the shift of
     * the matching meal is opened without it being canceled.
     */
    public void makeDefinitive() {
        //TODO
        //@author Meireles
        // is there business logic missing? How should it interact with shift opening?
        if (this.state != BookingState.DONE) {
            throw new IllegalStateException();
        }
        this.state = BookingState.DEFINITIVE;
    }

    /**
     * It delivers the booking by changing it state from "Definitive" to
     * "Delivered". A booking is considered to be delivered after it has been
     * delivered on the cash register.
     */
    public void deliver() {
        //TODO
        //@author Meireles
        // is there business logic missing? How should it interact with the meal delivery?
        if (this.state != BookingState.DEFINITIVE) {
            throw new IllegalStateException();
        }
        this.state = BookingState.DELIVERED;
    }

    /**
     * It wastes the booking by changing it state from "Definitive" to "Wasted".
     * A booking is considered to be wasted when the shift of the matching meal
     * is closed without it being delivered.
     */
    public void waste() {
        //TODO
        //@author Meireles
        // is there business logic missing? How should it interact with shift closing?
        if (this.state != BookingState.DEFINITIVE) {
            throw new IllegalStateException();
        }
        this.state = BookingState.WASTED;
    }

    public Meal meal() {
        return this.meal;
    }

    //FIXME
    //@author Meireles
    // is this method really necessary? Why expose private data?
    // maybe it could be replaced by the "isAtState" method?
    public BookingState state() {
        return this.state;
    }

//    public void validateBookingDate(Meal meal) {
//        Calendar cal = Calendar.getInstance();
//        Calendar mealDate = meal.getDate();
//        if (mealDate.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)
//                && mealDate.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
//                && mealDate.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) {
//            if (cal.get(Calendar.HOUR_OF_DAY) > meal.mealType().freeBookingCancellationTimeLimit().get(Calendar.HOUR_OF_DAY)) {
//                throw new IllegalStateException();
//            }
//        }
//    }
}
