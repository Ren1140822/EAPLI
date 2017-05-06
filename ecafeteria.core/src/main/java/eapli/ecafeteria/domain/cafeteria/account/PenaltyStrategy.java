/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.Money;

/**
 * It provides the tools for the refunding penalty. It fulfills the Strategy
 * Pattern.
 *
 * @author Meireles
 */
public interface PenaltyStrategy {

    /**
     * It applies the penalty to the money amount.
     *
     * @param toAmount The original money amount.
     * @return The remaining money amount after the penalty.
     */
    Money apply(Money toAmount);

}
