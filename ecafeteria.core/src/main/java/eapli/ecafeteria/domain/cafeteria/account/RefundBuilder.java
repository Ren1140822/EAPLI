/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.Factory;
import java.util.Calendar;

/**
 * A builder for the Refund. It allows a step-by-step creation. It must first
 * define the mecanographic number, amount and penalty, and then it can be
 * built.
 *
 * @author Meireles
 */
public class RefundBuilder implements Factory<Refund> {

    private MecanographicNumber number;
    private Money amount;
    private PenaltyStrategy penalty;

    /**
     * Starting refund builder. All components are null.
     */
    public RefundBuilder() {
        number = null;
        amount = null;
        penalty = null;
    }

    /**
     * It stores the mecanographic number to be used on the refund.
     *
     * @param number The mecanographic number of the cafeteria user to be
     * refunded.
     * @return It returns this builder with the mecanographic number.
     */
    public RefundBuilder withMecanographicNumber(MecanographicNumber number) {
        this.number = number;
        return this;
    }

    /**
     * It stores the money amount to be used on the refund.
     *
     * @param amount The money amount to be refunded.
     * @return It returns this builder with the money amount.
     */
    public RefundBuilder withMoney(Money amount) {
        this.amount = amount;
        return this;
    }

    /**
     * It checks if both dates references the same day.
     *
     * @param thisDate The first date to be examined.
     * @param thatDate The second date to be examined.
     * @return It returns "true" if both dates have the same day, month and
     * year.
     */
    private boolean isAtSameDay(Calendar thisDate, Calendar thatDate) {
        return thisDate.get(Calendar.YEAR) == thatDate.get(Calendar.YEAR) && thisDate.get(Calendar.MONTH) == thatDate.get(Calendar.MONTH) && thisDate.get(Calendar.DAY_OF_MONTH) == thatDate.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * It checks if the time (hours/minutes/seconds) of the cancellation time is
     * after the limit defined by the meal type.
     *
     * @param mealType The meal type of the booking that is being canceled.
     * @param cancelTime The cancellation time.
     * @return It returns "true" if the cancelation is past time limit or
     * "false" otherwise.
     */
    private boolean isAfterTimeLimit(MealType mealType, Calendar cancelTime) {
        if (mealType == null) {
            throw new IllegalStateException("unrecognized meal type");
        }
        Calendar limit = mealType.freeBookingCancellationTimeLimit();
        int hourDifference = cancelTime.get(Calendar.HOUR) - limit.get(Calendar.HOUR);
        if (hourDifference > 0) {
            return true;
        } else if (hourDifference < 0) {
            return false;
        }
        int minuteDifference = cancelTime.get(Calendar.MINUTE) - limit.get(Calendar.MINUTE);
        if (minuteDifference > 0) {
            return true;
        } else if (minuteDifference < 0) {
            return false;
        }
        return cancelTime.get(Calendar.SECOND) >= limit.get(Calendar.SECOND);
    }

    /**
     * It stores the penalty strategy to be used on the refund.
     *
     * @param mealDate The date of the meal booked.
     * @param mealType The meal type of the booked meal.
     * @param cancelDate The date on which the cancellation was performed.
     * @return It returns this builder with the penalty strategy.
     */
    public RefundBuilder withPenalty(Calendar mealDate, MealType mealType, Calendar cancelDate) {
        if (isAtSameDay(mealDate, cancelDate) && isAfterTimeLimit(mealType, cancelDate)) {
            penalty = new PenaltyFiftyPercent();
        } else {
            penalty = new PenaltyFree();
        }
        return this;
    }

    /**
     * It builds the refund with the mecanographic number and the money amount
     * resulting from the penalty appliance.
     *
     * @return It returns a valid Refund.
     */
    @Override
    public Refund build() {
        if (number == null || amount == null || penalty == null) {
            throw new IllegalStateException("object is malformed");
        }
        amount = penalty.apply(amount);
        return new Refund(number, amount);
    }

}
