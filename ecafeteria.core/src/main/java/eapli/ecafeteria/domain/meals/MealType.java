/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

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

    public enum MealTypes {
        //FIXME
        //@author Meireles
        // Should these variables be refactored to English?
        ALMOCO, JANTAR
    };

    private MealTypes mealType;

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
            case ALMOCO:
                limit.set(Calendar.HOUR, ALMOCO_FREE_CANCEL_TIME_LIMIT_HOUR);
                limit.set(Calendar.MINUTE, ALMOCO_FREE_CANCEL_TIME_LIMIT_MINUTES);
                limit.set(Calendar.SECOND, ALMOCO_FREE_CANCEL_TIME_LIMIT_SECONDS);
                break;
            case JANTAR:
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

}
