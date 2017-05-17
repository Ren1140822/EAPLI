/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.persistence.AccountCardRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.Factory;

/**
 *
 * @author PC
 */

/**
 * A builder for the Purchase. It allows a step-by-step creation. It must first
 * define the account card and amount. Then it can be built.
 * */
public class PurchaseBuilder implements Factory<Purchase> {
    
    private AccountCard card;
    private Money amount;

    /**
     * Starting refund builder. All components are null.
     */
    public PurchaseBuilder() {
        card = null;
        amount = null;
    }
    
        /**
     * It stores the account card to be used on the purchase.
     *
     * @param card The account card of the cafeteria user .
     * @return It returns this builder with the account card.
     */
    public PurchaseBuilder withAccountCard(AccountCard card) {
        this.card = card;
        return this;
    }
    
        /**
     * It stores the account card to be used on the purchase.
     *
     * @param number The mecanographic number whose will be used to find the
     * account card of the cafeteria user 
     * @return It returns this builder with the account card.
     */
    public PurchaseBuilder withAccountCard(MecanographicNumber number) {
        final AccountCardRepository cardsRepository = PersistenceContext.repositories().accountCards(null);
        this.card = cardsRepository.findByMecanographicNumber(number);
        return this;
    }
    
     /**
     * It stores the money amount to be used on the refund.
     *
     * @param amount The money amount to be refunded.
     * @return It returns this builder with the money amount.
     */
    public PurchaseBuilder withMoney(Money amount) {
        this.amount = amount;
        return this;
    }
    
        /**
     * It builds the purchase with the mecanographic number and the money amount
     * @return It returns a valid Purchase.
     */
    @Override
    public Purchase build() {
        if (card == null || amount == null ) {
            throw new IllegalStateException("object is malformed");
        }
       
        return new Purchase(card, amount);
    }
}
