/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.Money;

/**
 * The penalty strategy to be used when no penalty should be applied.
 * 
 * @author Meireles
 */
public class PenaltyFree implements PenaltyStrategy {

    /**
     * It applies no penalty to the amount.
     * 
     * @param toAmount The amount on which to apply the penalty.
     * @return It returns the same Money amount.
     */
    @Override
    public Money apply(Money toAmount) {
        return toAmount;
    }

}
