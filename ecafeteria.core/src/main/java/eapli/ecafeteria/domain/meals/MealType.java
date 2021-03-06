/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ddd.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @FIXME javadoc
 */
@Embeddable
public class MealType implements ValueObject, Serializable {

    /**
     * The booking cancellation time limit for Almoco.
     */
    private static final int ALMOCO_FREE_CANCEL_TIME_LIMIT_HOUR = 10;
    private static final int ALMOCO_FREE_CANCEL_TIME_LIMIT_MINUTES = 0;
    private static final int ALMOCO_FREE_CANCEL_TIME_LIMIT_SECONDS = 0;

    /**
     * The booking cancellation time limit for Jantar.
     */
    private static final int JANTAR_FREE_CANCEL_TIME_LIMIT_HOUR = 16;
    private static final int JANTAR_FREE_CANCEL_TIME_LIMIT_MINUTES = 0;
    private static final int JANTAR_FREE_CANCEL_TIME_LIMIT_SECONDS = 0;
    private MealTypes mealType;
    ;

    protected MealType() {
    } // For ORM

    public MealType(MealTypes mealType) {
        if (mealType == null) {
            throw new IllegalStateException();
        }
        this.mealType = mealType;
    }

    public String mealType() {
        return this.mealType.name();
    }

    public int mealTypeID() {
        switch (mealType) {
            case LUNCH:
                return 0;
            case DINNER:
                return 1;
        }
        return -1; // should not happen
    }
    
    /**
     * It provides the time until the bookings can be canceled.
     *
     * @return It returns a Calendar with only the hours, minutes and seconds
     * set with the time limit for free cancellations. It returns null if the
     * meal type is not recognized.
     */
    public Calendar freeBookingCancellationTimeLimit() {
        Calendar limit = Calendar.getInstance();
        limit.clear();
        switch (mealType) {
            case LUNCH:
                limit.set(Calendar.HOUR, ALMOCO_FREE_CANCEL_TIME_LIMIT_HOUR);
                limit.set(Calendar.MINUTE, ALMOCO_FREE_CANCEL_TIME_LIMIT_MINUTES);
                limit.set(Calendar.SECOND, ALMOCO_FREE_CANCEL_TIME_LIMIT_SECONDS);
                break;
            case DINNER:
                limit.set(Calendar.HOUR, JANTAR_FREE_CANCEL_TIME_LIMIT_HOUR);
                limit.set(Calendar.MINUTE, JANTAR_FREE_CANCEL_TIME_LIMIT_MINUTES);
                limit.set(Calendar.SECOND, JANTAR_FREE_CANCEL_TIME_LIMIT_SECONDS);
                break;
            default:
                limit = null;
                break;
        }
        return limit;
    }

    /**
     * It checks if the meal type (enum) is the same as this meal type.
     *
     * @param type The meal type (enum) to check.
     * @return It returns "true" if this meal type has the same meal type (enum) or "false" otherwise.
     */
    public boolean isOf(MealTypes type) {
        return type!=null && mealType.equals(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MealType meal = (MealType) o;

        return mealType.equals(meal.mealType);
    }

    public enum MealTypes {
        LUNCH, DINNER
    }
}
