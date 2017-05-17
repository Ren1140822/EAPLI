/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ddd.ValueObject;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * It represents the Rating.
 *
 * @author Sofia Gonçalves [1150657@isep.ipp.pt] Pedro Chilro
 * [1150019@isep.ipp.pt]
 */
@Embeddable
public class Rating implements ValueObject {

    /**
     * The minimum number of the rating.
     */
    private static final int MINIMUM_RATING = 1;

    /**
     * The maximum number of the rating.
     */
    private static final int MAXIMUM_RATING = 5;

    /**
     * The number of the rate.
     */
    private Integer rate;

    protected Rating() {
        //for ORM
    }

    /**
     * The constructor of Rating.
     *
     * @param rate It receives a rate that has to be greater or equal than
     * MINIMUM_RATING and lesser or equal than MAXIMUM_RATING.
     */
    public Rating(Integer rate) {
        if (rate < MINIMUM_RATING || rate > MAXIMUM_RATING) {
            throw new IllegalStateException("Rating must be between 1 and 5.");
        }
        this.rate = rate;
    }

    /**
     * The override method of toString.
     *
     * @return It returns 'Rate:' with the respective rate number.
     */
    @Override
    public String toString() {
        return "Rate: " + this.rate;
    }

    /**
     * It states if two objects are equals.
     *
     * @param obj The other object to be compared.
     * @return It returns true if the objects are equals or false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Rating other_obj = (Rating) obj;
        return this.rate.equals(other_obj.rate);
    }

    /**
     * The override hashcode method.
     *
     * @return It returns the number of hashcode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.rate);
        return hash;
    }

}
