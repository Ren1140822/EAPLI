package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * The quantity of meals prepared from a Meal.
 *
 * @TODO consider having a MealID instead of using the complete Meal object as
 * the business id for this aggregate
 *
 * @author Diogo Santos [1150451@isep.ipp.pt] Sofia Silva [1150690@isep.ipp.pt]
 */
@Entity
public class MealsPrepared implements AggregateRoot<Meal> {

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    private Meal meal;
    private int quantity;

    /**
     * Protect Construtor for ORM
     */
    protected MealsPrepared() {
        // for ORM
    }

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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean is(Meal id) {
        return this.meal.equals(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Meal id() {
        return this.meal;
    }

}
