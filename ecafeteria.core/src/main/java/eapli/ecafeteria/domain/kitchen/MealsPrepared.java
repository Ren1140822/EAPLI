/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.AggregateRoot;
import javax.persistence.EmbeddedId;
import javax.persistence.Version;


/**
 *
 * @author Diogo Santos [1150451@isep.ipp.pt] Sofia Silva [1150690@isep.ipp.pt]
 */
public class MealsPrepared implements AggregateRoot<Meal> {
   
    @Version
    private Long version;

    @EmbeddedId
    private Meal meal;

    private int quantity;
  
    /**
     * Prepared Meals Constructor.
     *
     * @param meal the meal associated
     * @param quantity the quantity of prepared meals
     */
    public MealsPrepared(Meal meal, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("The quantity of prepared meals must be a non negative number.");
        }
        if (meal == null) {
            throw new IllegalArgumentException("The meal can´t be null.");
        }
        this.quantity = quantity;
        this.meal = meal;
    }    

    @Override
    public boolean sameAs(Object other) {
       if (!(other instanceof MealsPrepared)) {
            return false;
        }

        final MealsPrepared that = (MealsPrepared) other;
        if (this == that) {
            return true;
        }
        if (!this.meal.equals(that.meal)) {
            return false;
        }
        return this.quantity == that.quantity;
    }

    @Override
    public boolean is(Meal id) {
       return this.meal.equals(id);
    }

    @Override
    public Meal id() {
        return this.meal;
    }

    
}