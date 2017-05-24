/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.framework.domain.ddd.Factory;

/**
 * A factory for Complaint entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 */
public class ComplaintBuilder implements Factory<Complaint> {

    private String text;
    private Dish dish;
    private MecanographicNumber number;

    public ComplaintBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public ComplaintBuilder withDish(Dish dish) {
        this.dish = dish;
        return this;
    }

    public ComplaintBuilder withMecanograficNumber(MecanographicNumber number) {
        this.number = number;
        return this;
    }

    @Override
    public Complaint build() {
        if (dish == null && number == null) {
            return new Complaint(text);
        }

        if (dish == null) {
            return new Complaint(text, number);
        }

        if (number == null) {
            return new Complaint(text, dish);
        }

        return new Complaint(text, dish, number);
    }
}
