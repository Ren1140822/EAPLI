/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.Money;
import javax.persistence.Entity;

/**
 *
 * @author PC
 */
@Entity
public class Purchase extends Transaction {

    protected Purchase() {
        // for ORM only
    }

    /**
     *
     * @param accountCard The account card of the cafeteria user to Withdrawn
     * @param amount the amount to be Withdrawn.
     */
    protected Purchase(AccountCard accountCard, Money amount) {
        super(accountCard, amount);
        if (amount.amount() > 0) {
            throw new IllegalStateException("a refund musthave a negative amount");
        }
    }
    
        /**
     * It checks if the object is equal to this purchase.
     *
     * @param o The object to be examined.
     * @return It returns "true" if both objects are equal or "false" otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && (o instanceof Purchase);
    }

}
