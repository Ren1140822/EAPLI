/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Diogo Santos [1150451@isep.ipp.pt] Sofia Silva [1150690@isep.ipp.pt]
 */
@Embeddable
public class MealsPrepared implements ValueObject, Serializable {

    /**
     * The quantity of prepared meals.
     */
    private int quantity;

    /**
     * Prepared Meals Constructor.
     *
     * @param quantity the quantity of prepared meals
     */
    public MealsPrepared(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("The quantity of prepared meals must be a non negative number.");
        }
        this.quantity = quantity;
    }

}
