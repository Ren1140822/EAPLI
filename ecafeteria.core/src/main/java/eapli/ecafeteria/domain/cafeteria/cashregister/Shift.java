package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @FIXME javadoc
 * @FIXME is this an entity, a value object or an aggregate?
 *
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 *
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

    public Shift(Calendar date, MealType mealType) {
        if (date == null || mealType == null) {
            throw new IllegalStateException("A shift must have a date and a meal type!");
        }
        this.date = date;
        this.mealType = mealType;
        this.state = ShiftState.CLOSED;
    }

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

    public boolean isAtDate(Calendar date) {
        return this.date.equals(date);
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
