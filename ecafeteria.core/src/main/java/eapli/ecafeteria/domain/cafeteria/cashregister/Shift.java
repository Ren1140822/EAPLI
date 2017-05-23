package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.ecafeteria.domain.meals.MealType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a shift.
 *
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 * @FIXME is this an entity, a value object or an aggregate?
 */
@Entity
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private MealType mealType;
    @Temporal(TemporalType.DATE)
    private Calendar date;
    private ShiftState state;

    protected Shift() {
        // for ORM only
    }

    /**
     * Constructs an instance of Shift with the date and meal type passed as
     * parameters.
     *
     * @param date     the date
     * @param mealType the meal type
     */
    public Shift(Calendar date, MealType mealType) {
        if (date == null || mealType == null) {
            throw new IllegalStateException("A shift must have a date and a meal type!");
        }
        this.date = date;
        this.mealType = mealType;
        this.state = ShiftState.OPENED;
    }

    /**
     * Checks if the meal type passed as parameter is the same.
     *
     * @param mealType the meal type to be compared to.
     * @return true if the meal type is the same, false otherwise.
     */
    public boolean isOfMealType(MealType mealType) {
        return this.mealType.equals(mealType);
    }

    /**
     * Checks if the Shift is currently at a certain state.
     *
     * @param state The state to be compared to.
     * @return returns true if the shift is currently at the received state,
     * false otherwise.
     */
    public boolean isAtState(ShiftState state) {
        return this.state.equals(state);
    }

    /**
     * Checks if the Shift is currently at a certain date.
     *
     * @param date the date to be compared to.
     * @return returns true if the shift is currently at the received date,
     * false otherwise.
     */
    public boolean isAtDate(Calendar date) {
        return this.date.equals(date);
    }

    /**
     * Modifies a shift state to closed.
     */
    public void close() {
        if (this.state != ShiftState.OPENED) {
            throw new IllegalStateException("Shift must be open before closing!");
        }
        this.state = ShiftState.CLOSED;
    }

    /**
     * Retrieves the date.
     *
     * @return date
     */
    public Calendar date() {
        return date;
    }

    /**
     * Retrieves the meal type.
     *
     * @return meal type
     */
    public MealType mealType() {
        return mealType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object otherObject) {
        if ((otherObject == null) || !(otherObject instanceof Shift)) {
            return false;
        }
        if (this == otherObject) {
            return true;
        }

        final Shift otherShift = (Shift) otherObject;

        return this.date.equals(otherShift.date) && this.mealType.equals(otherShift.mealType);
    }

}