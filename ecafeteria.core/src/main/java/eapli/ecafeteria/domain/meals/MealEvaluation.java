/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.booking.Booking;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 *
 * @author Sofia Gonçalves [1150657@isep.ipp.pt] Pedro Chilro
 * [1150019@isep.ipp.pt]
 */
@Entity
public class MealEvaluation implements Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long pk;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Booking booking;

    @Embedded
    private Rating rating;

    @Embedded
    private Comment comment;

    protected MealEvaluation() {
        //for ORM
    }

    public MealEvaluation(Booking booking, Rating rating, Comment comment) {
        if (booking == null || rating == null) {
            throw new IllegalStateException();
        }
        this.booking = booking;
        this.rating = rating;
        this.comment = comment;
    }

    public MealEvaluation(Booking booking, Rating rating) {
        if (booking == null || rating == null) {
            throw new IllegalStateException();
        }
        this.booking = booking;
        this.rating = rating;
    }

}