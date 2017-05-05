/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.domain.Money;
import javax.persistence.Entity;

/**
 * It represents the refund created by a Booking Cancellation. It is a
 * Transaction.
 *
 * @author Meireles
 */
@Entity
public class Refund extends Transaction {

    protected Refund() {
        // for ORM only
    }

    /**
     * Refund should be created using the RefundBuilder.
     *
     * @param mecanographicNumber The mecanographic number of the cafeteria user
     * to refund.
     * @param amount The amount to be refunded.
     */
    protected Refund(MecanographicNumber mecanographicNumber, Money amount) {
        super(mecanographicNumber, amount);
    }

    /**
     * It checks if the object is equal to this refund.
     *
     * @param o The object to be examined.
     * @return It returns "true" if both objects are equal or "false" otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && (o instanceof Refund);
    }

}
