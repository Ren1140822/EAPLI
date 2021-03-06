/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.account.Purchase;
import eapli.ecafeteria.domain.cafeteria.account.PurchaseBuilder;
import eapli.ecafeteria.domain.cafeteria.account.Refund;
import eapli.ecafeteria.domain.cafeteria.account.RefundBuilder;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.util.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @FIXME javadoc
 * @FIXME create unit tests
 * @author Nuno Pinto [1150838@isep.ipp.pt] Henrique Oliveira
 * [1150738@isep.ipp.pt]
 */
@Entity
public class Booking implements AggregateRoot<BookingID>, Serializable {

    @Version
    private Long version;
    @Id
    @GeneratedValue
    private Long pk;

    @Column(unique = true)
    private BookingID id;

    //FIXME cascade should be NONE
    @ManyToOne(cascade = CascadeType.MERGE)
    private CafeteriaUser user;

    //FIXME cascade should be NONE
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
        this.id = BookingID.randomBookingID();
        this.user = user;
        this.meal = meal;
        this.state = BookingState.DONE;
    }

    /**
     * It cancels the booking by changing its state from "Done" to "Canceled".
     * It throws an IllegalStateException if the booking is in a non-cancellable
     * state.
     */
    public void cancel() {
        if (this.state != BookingState.DONE) {
            throw new IllegalStateException();
        }
        this.state = BookingState.CANCELED;
    }

    /**
     * It provides the corresponding refund from this booking.
     *
     * @return It returns the refund from this booking.
     */
    public Refund refund() {
        RefundBuilder refund = new RefundBuilder();
        refund.withPenalty(meal.getDate(), meal.mealType(), Calendar.getInstance());
        refund.withAccountCard(user.mecanographicNumber());
        refund.withMoney(meal.dish().currentPrice());
        return refund.build();
    }

    public Purchase purchase() {
        PurchaseBuilder purchase = new PurchaseBuilder();
        purchase.withAccountCard(user.mecanographicNumber());
        purchase.withMoney(meal.dish().currentPrice().negate());
        return purchase.build();
    }

    public boolean belongsTo(CafeteriaUser user) {
        return this.user.equals(user);
    }

    public boolean isOfMeal(Meal meal) {
        return this.meal.equals(meal);
    }

    public boolean isOfMealType(MealType type) {
        return this.meal.isOfMealType(type);
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

    public boolean isSameDate(Calendar date) {
        return DateTime.isSameDate(meal.getDate(), date);
    }

    /**
     * It checks if the booking's meal occurs until a certain date (Year, Month
     * and Day regardless of the time).
     *
     * @param date The limit date.
     * @return It returns "true" if the meal date occurs before or at the same
     * day as the limit date or "false" otherwise.
     */
    public boolean isUntilDate(Calendar date) {
        return DateTime.isBefore(meal.getDate(), date) || DateTime.isSameDate(meal.getDate(), date);
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
    public boolean compareDate(Booking otherBooking) {
        return this.meal.getDate().after(otherBooking.meal.getDate());
    }

    @Override
    public boolean is(BookingID id) {
        return id.equals(id);
    }

    @Override
    public BookingID id() {
        return id;
    }

    @Override
    public boolean sameAs(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null || other.getClass() != getClass()) {
            return false;
        }

        Booking that = (Booking) other;
        return user.equals(that.user) && meal.equals(that.meal) && state.equals(that.state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Booking booking = (Booking) o;

        return id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
