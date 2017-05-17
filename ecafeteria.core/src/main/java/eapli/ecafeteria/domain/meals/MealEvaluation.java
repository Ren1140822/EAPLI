/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 * @TODO why is this an aggregate? are the decisions registered in the design of
 * the use case?
 * @author Sofia Gonçalves [1150657@isep.ipp.pt] Pedro Chilro
 * [1150019@isep.ipp.pt]
 */
@Entity
public class MealEvaluation implements AggregateRoot<Booking>, Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long pk;

    /**
     * The booking of the MealEvaluation.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    private Booking booking;

    /**
     * The rating of the MealEvaluation.
     */
    @Embedded
    private Rating rating;

    /**
     * The comment of the MealEvaluation.
     */
    @Embedded
    private Comment comment;

    protected MealEvaluation() {
        //for ORM
    }

    /**
     * The constructor of MealEvaluation.
     *
     * @param booking the booking to be evaluated that can't be null.
     * @param rating The rating of the evaluation that can't be null.
     * @param comment The comment of the evaluation.
     */
    public MealEvaluation(Booking booking, Rating rating, Comment comment) {
        if (booking == null || rating == null) {
            throw new IllegalStateException();
        }
        this.booking = booking;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Checks if current evaluation is an evaluation of desired meal.
     *
     * @param meal The Meal.
     * @return It returns true if the current evaluation is an evaluation of
     * desired meal or false otherwise.
     */
    public boolean isOfMeal(Meal meal) {
        return this.booking.isOfMeal(meal);
    }

    /**
     * Override method that states if objects are the same.
     *
     * @param o The object to be compared.
     * @return It returns true if the objects are the same or false otherwise.
     */
    @Override
    public boolean sameAs(Object o) {
        if (!(o instanceof MealEvaluation)) {
            return false;
        }
        final MealEvaluation other = (MealEvaluation) o;
        if (this == other) {
            return true;
        }
        if (!this.booking.equals(other.booking)) {
            return false;
        }
        if (!this.comment.equals(other.comment)) {
            return false;
        }
        return this.rating.equals(other.rating);
    }

    /**
     * The method that gives the id of the MealEvaluation, that is a booking.
     *
     * @return It returns the respective booking.
     */
    @Override
    public Booking id() {
        return this.booking;
    }

    /**
     * It states if the booking is the same of the object.
     *
     * @param id the id to be compare, that is a booking.
     * @return It returns true if the id is the same or false otherwise.
     */
    @Override
    public boolean is(Booking id) {
        return id().equals(id);
    }

}
