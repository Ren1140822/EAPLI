/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

/**
 * The booking state starts as Done, then it might become Canceled (final state)
 * or Definitive. From there it might become Wasted or Delivered (both final
 * states).
 *
 * @author Miguel Silva - 1150901
 */
public enum BookingState {

    /**
     * It does not grant a meal delivery. It can not be canceled. Unaffected by
     * shift. Preceding state is Done. It is a final state.
     */
    CANCELED,
    /**
     * It grants a meal delivery. It can no longer be canceled. Shift closing
     * might change it to Wasted. Preceding state is Done. It might become
     * Delivered if the meal is delivered or Wasted if the shift closes without
     * the meal delivery.
     */
    DEFINITIVE,
    /**
     * It does not grant a meal delivery. It can no longer be canceled.
     * Unaffected by shift. Preceding state is Definitive. It is a final state.
     */
    DELIVERED,
    /**
     * It does not grant a meal delivery. It can be canceled. Shift opening
     * might change it to Definitive. It is the initial state. It might become
     * Canceled if the booking is canceled or Definitive when the corresponding
     * shift is open.
     */
    DONE,
    /**
     * It does not grant a meal delivery. It can no longer be canceled.
     * Unaffected by shift. Preceding state is Definitive. It is a final state.
     */
    WASTED;

}
