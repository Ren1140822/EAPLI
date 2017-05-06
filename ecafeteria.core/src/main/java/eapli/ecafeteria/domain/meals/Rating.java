/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @FIXME javadoc
 * @FIXME is this an entity, a value object or an aggregate?
 * @author Sofia Gonçalves [1150657@isep.ipp.pt] Pedro Chilro
 * [1150019@isep.ipp.pt]
 */
@Embeddable
public class Rating implements Serializable {

    private Integer rate;

    protected Rating() {
        //for ORM
    }

    /**
     *
     * @param rate
     */
    public Rating(Integer rate) {
        if (rate < 1 || rate > 5) {
            throw new IllegalStateException("Rating must be in between 1 and 5.");
        }
        this.rate = rate;
    }
}
