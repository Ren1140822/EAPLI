/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.Money;

/**
 * The penalty strategy to be used when fifty percent penalty should be applied.
 * 
 * @author Meireles
 */
public class PenaltyFiftyPercent implements PenaltyStrategy {

    /**
     * The penalty percentage to be used.
     */
    private static final double PENALTY_PERCENTAGE = 0.5;

    /**
     * It applies fifty percent penalty to the amount.
     * 
     * @param toAmount The amount on which to apply the penalty.
     * @return It returns the half the Money amount.
     */
    @Override
    public Money apply(Money toAmount) {
        return toAmount.multiply(PENALTY_PERCENTAGE);
    }

}
